package com.fotova.service.order;

import com.fotova.dto.BillingDetailDtoAmq;
import com.fotova.dto.ProductBillingDto;
import com.fotova.dto.order.OrderBasketDto;
import com.fotova.dto.order.OrderClientDto;
import com.fotova.dto.order.OrderDto;
import com.fotova.dto.orderProduct.OrderBillingDto;
import com.fotova.dto.orderProduct.OrderProductBillingDto;
import com.fotova.dto.orderProduct.OrderProductDto;
import com.fotova.dto.stripe.StripOrderBasket;
import com.fotova.dto.stripe.StripeProductRequest;
import com.fotova.entity.ClientEntity;
import com.fotova.entity.OrderEntity;
import com.fotova.entity.ProductEntity;
import com.fotova.service.order.helper.OrderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class OrderMapper {

    @Autowired
    private OrderHelper orderHelper;

    public List<OrderDto> mapToOrderDtoList(List<OrderEntity> orderEntities){
        List<OrderDto> orderDtoList = new ArrayList<>();

        for(OrderEntity orderEntity : orderEntities){
            OrderDto orderDto = mapToOrderDto(orderEntity);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    public BillingDetailDtoAmq mapToBillingDetailDtoAmq(
            List<OrderBasketDto> orderBasketDtos,
            List<ProductEntity> productEntities) {

        if (orderBasketDtos == null || orderBasketDtos.isEmpty()) {
            throw new IllegalArgumentException("Order basket cannot be empty");
        }

        // 🔹 On construit une map productId → ProductEntity pour accès rapide
        Map<Integer, ProductEntity> productMap = productEntities.stream()
                .collect(Collectors.toMap(ProductEntity::getId, p -> p));

        List<ProductBillingDto> productBillingDtos = new ArrayList<>();

        for (OrderBasketDto order : orderBasketDtos) {

            ProductEntity product = productMap.get(order.getProductId());

            if (product == null) {
                throw new RuntimeException(
                        "Product not found for id: " + order.getProductId());
            }

            Double unitPrice = product.getPrice();
            Integer quantity = order.getQuantity();
            Double totalPrice = unitPrice * quantity;

            ProductBillingDto productBillingDto = new ProductBillingDto();
            productBillingDto.setProductName(product.getName());
            productBillingDto.setPrice(unitPrice);
            productBillingDto.setQuantity(quantity);
            productBillingDto.setTotalPrice(totalPrice);

            productBillingDtos.add(productBillingDto);
        }

        BillingDetailDtoAmq billing = new BillingDetailDtoAmq();
        billing.setEmail(orderBasketDtos.get(0).getEmail()); // même email pour tout le panier
        billing.setProductBillingDtos(productBillingDtos);

        return billing;
    }


    public OrderDto mapToOrderDto(OrderEntity orderEntity){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderEntity.getId());
        orderDto.setCreateAt(orderEntity.getCreateAt());
        orderDto.setIsDone(orderEntity.getIsDone());

        if(orderEntity.getClient()!=null){
            OrderClientDto orderClientDto = new OrderClientDto();
            orderClientDto.setId(orderEntity.getClient().getId());
            orderClientDto.setEmail(orderEntity.getClient().getEmail());
            orderDto.setClient(orderClientDto);
        }
        return orderDto;
    }

    public OrderEntity mapToOrderEntity(OrderDto orderDto, Integer clientId){

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCreateAt(orderDto.getCreateAt());
        orderEntity.setIsDone(orderDto.getIsDone());

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(clientId);
        clientEntity.setEmail(orderDto.getClient().getEmail());

        orderEntity.setClient(clientEntity);

        return orderEntity;
    }

    public OrderProductBillingDto  mapToOrderProductBillingDto(List<OrderProductDto> productDtoList)
    {
        OrderProductBillingDto  orderProductBillingDto = new OrderProductBillingDto();
        orderProductBillingDto.setOrderId(productDtoList.get(0).getOrderId());
        orderProductBillingDto.setCreationDate(productDtoList.get(0).getCreationDate());
        orderProductBillingDto.setTotal(orderHelper.computeBillingTotal(productDtoList));

        List<OrderBillingDto> productBillingDtoList  = new ArrayList<>();

        for(OrderProductDto orderProductDto : productDtoList){
            OrderBillingDto orderBillingDto = new OrderBillingDto();
            orderBillingDto.setName(orderProductDto.getName());
            orderBillingDto.setPrice(orderProductDto.getPrice());
            orderBillingDto.setQuantity(orderProductDto.getQuantity());
            productBillingDtoList.add(orderBillingDto);
        }

        orderProductBillingDto.setProductBillingDtoList(productBillingDtoList);

        return orderProductBillingDto;
    }

    public List<OrderBasketDto> mapOrderBasketWithStripeRequest(StripeProductRequest productRequest) {

        String uuid = productRequest.getName();
        String email = productRequest.getEmail();
        List<OrderBasketDto> orderBasketDtoList = new ArrayList<>();

        for(StripOrderBasket stripOrderBasket:productRequest.getProductBasket()) {
            OrderBasketDto orderBasketDto = new OrderBasketDto();
            orderBasketDto.setId(UUID.randomUUID().toString());
            orderBasketDto.setVerificationCode(uuid);
            orderBasketDto.setProductId(stripOrderBasket.getProductId());
            orderBasketDto.setQuantity(stripOrderBasket.getQuantity());
            orderBasketDto.setEmail(email);
            orderBasketDtoList.add(orderBasketDto);
        }

        return orderBasketDtoList;
    }
}
