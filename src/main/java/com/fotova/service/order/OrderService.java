package com.fotova.service.order;

import com.fotova.dto.order.OrderBasketDto;
import com.fotova.dto.order.OrderDto;
import com.fotova.dto.orderProduct.OrderProductBillingDto;
import com.fotova.dto.orderProduct.OrderProductDto;
import com.fotova.dto.stripe.StripeProductRequest;
import com.fotova.entity.ClientEntity;
import com.fotova.entity.OrderEntity;
import com.fotova.entity.OrderProductEntity;
import com.fotova.entity.ProductEntity;
import com.fotova.repository.client.ClientRepositoryImpl;
import com.fotova.repository.redis.OrderBasketRepositoryImpl;
import com.fotova.repository.order.OrderProductRepositoryImpl;
import com.fotova.repository.order.OrderRepositoryImpl;
import com.fotova.repository.product.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepositoryImpl orderRepository;

    @Autowired
    private OrderProductRepositoryImpl orderProductRepository;

    @Autowired
    private ClientRepositoryImpl clientRepository;

    @Autowired
    private ProductRepositoryImpl productRepository;

    @Autowired
    private OrderBasketRepositoryImpl orderBasketRepository;

    @Autowired
    private OrderMapper orderMapper;

    public StripeProductRequest setStripeProductRequestName(StripeProductRequest stripeProductRequest) {
        stripeProductRequest.setName(UUID.randomUUID().toString());
        return stripeProductRequest;
    }

    public List<OrderDto> getAllOrders() {
        List<OrderEntity> orderEntityList = orderRepository.findAll();
        return orderMapper.mapToOrderDtoList(orderEntityList);
    }

    public OrderDto getOrderById(Integer orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId);
        return orderMapper.mapToOrderDto(orderEntity);
    }

    public OrderDto addOrder(OrderDto orderDto) {

        Optional<ClientEntity> clientEntity = clientRepository.findFirstByEmail(orderDto.getClient().getEmail());
        OrderEntity orderEntity = new OrderEntity();

        if(clientEntity.isPresent())
        {
            orderEntity = orderMapper.mapToOrderEntity(orderDto, clientEntity.get().getId());
        }

        return orderMapper.mapToOrderDto(orderRepository.save(orderEntity));
    }

    public List<OrderProductDto> getOrderProductByEmail(String email) {
        return orderProductRepository.getOrderProductByEmail(email);
    }

    public OrderProductBillingDto  getOrderProductBillingByEmail(String email) {
        List<OrderProductDto> productDtoList = orderProductRepository.getOrderProductByEmail(email);

        return orderMapper.mapToOrderProductBillingDto(productDtoList);
    }

    private Boolean checkOrderBasket(String uuid) {

        for(OrderBasketDto orderBasketDto : ValidationOrderService.orderBasketDtoList) {
            if(orderBasketDto.getId().equals(uuid)) {
                return true;
            }
        }

        return false;
    }

    public String createOrderAfterShipment(String uuid) {

        Boolean checkOrder = this.checkOrderBasket(uuid);

        if(checkOrder) {
            OrderEntity orderEntity = new OrderEntity();
            String email = ValidationOrderService.orderBasketDtoList.get(0).getEmail();
            Optional<ClientEntity> clientEntity = clientRepository.findFirstByEmail(email);
            clientEntity.ifPresent(orderEntity::setClient);

            orderEntity.setCreateAt(Instant.now());
            OrderEntity orderCreated = orderRepository.save(orderEntity);

            // TODO ADAPT WITH REDIS
            for(OrderBasketDto temp : ValidationOrderService.orderBasketDtoList) {

                if(uuid.equals(temp.getId()))
                {
                    OrderProductEntity orderProductEntity = new OrderProductEntity();
                    ProductEntity productEntity = productRepository.findById(temp.getProductId());

                    orderProductEntity.setOrder(orderCreated);

                    orderProductEntity.setProduct(productEntity);
                    orderProductEntity.setQuantityProduct(temp.getQuantity());

                    orderProductRepository.save(orderProductEntity);
                }
            }
            return "Order created successfully with id : " + orderCreated.getId();
        }
        return "Order not created";
    }
}
