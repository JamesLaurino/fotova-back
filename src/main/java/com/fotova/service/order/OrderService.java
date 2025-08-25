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
import com.fotova.service.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

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

    public List<OrderProductDto> getOrderProductByEmail(String email, Integer orderId) {
        return orderProductRepository.getOrderProductByEmail(email,orderId);
    }

    public OrderProductBillingDto  getOrderProductBillingByEmail(String email,Integer orderId) {
        List<OrderProductDto> productDtoList = orderProductRepository.getOrderProductByEmail(email,orderId);

        return orderMapper.mapToOrderProductBillingDto(productDtoList);
    }

    public List<OrderProductDto> getOrderProductsByEmail(String email) {
        return orderProductRepository.getOrdersProductByEmail(email);
    }

    public List<OrderProductDto> getOrdersDetailed() {
        return orderProductRepository.getOrdersDetailed();
    }


    public void cleanOrderBasketByUUID(String uuid) {

        List<OrderBasketDto> orderBasketDtoUUID = getOrderBasketByUUID(uuid);

        for(OrderBasketDto orderBasketDto : orderBasketDtoUUID) {
            if(orderBasketDto.getVerificationCode().equals(uuid)) {
                orderBasketRepository.deleteOrderBasket(orderBasketDto);
            }
        }
    }

    public void fillOrderBasketWithStripeRequest(StripeProductRequest productRequest) {
        List<OrderBasketDto> orderBasketDtoList = orderMapper.mapOrderBasketWithStripeRequest(productRequest);
        orderBasketRepository.saveAllOrderBasket(orderBasketDtoList);
    }

    private List<OrderBasketDto> getOrderBasketByUUID(String uuid) {

        List<OrderBasketDto> orderBasketDtoList = orderBasketRepository.getAllOrderBasket();

        return orderBasketDtoList.stream()
                .filter(orderBasketDto -> {
                    if(orderBasketDto.getVerificationCode() != null && orderBasketDto.getVerificationCode().equals(uuid)) {return true;}
                    return false;
                })
                .collect(Collectors.toList());
    }

    private Boolean checkOrderBasket(String uuid) {
        return getOrderBasketByUUID(uuid).isEmpty();
    }

    public String createOrderAfterShipment(String uuid) {

        Boolean checkOrder = this.checkOrderBasket(uuid);
        List<OrderBasketDto> orderBasketDtoUUID = getOrderBasketByUUID(uuid);

        if(!checkOrder) {
            OrderEntity orderEntity = new OrderEntity();
            String email = orderBasketDtoUUID.get(0).getEmail();
            Optional<ClientEntity> clientEntity = clientRepository.findFirstByEmail(email);
            clientEntity.ifPresent(orderEntity::setClient);

            orderEntity.setCreateAt(Instant.now());
            OrderEntity orderCreated = orderRepository.save(orderEntity);

            for(OrderBasketDto orderBasketDto : orderBasketDtoUUID) {

                if(uuid.equals(orderBasketDto.getVerificationCode()))
                {
                    OrderProductEntity orderProductEntity = new OrderProductEntity();
                    ProductEntity productEntity = productRepository.findById(orderBasketDto.getProductId());

                    orderProductEntity.setOrder(orderCreated);

                    orderProductEntity.setProduct(productEntity);
                    orderProductEntity.setQuantityProduct(orderBasketDto.getQuantity());

                    orderProductRepository.save(orderProductEntity);
                }
            }
            return orderCreated.getId().toString();
        }
        return "Order not created";
    }

    public void sendRabbitMQOrder(String orderId) {
        rabbitMQProducer.sendMessage(orderId);
    }

    public void checkOrderQuantity(String uuid) {

        List<OrderBasketDto> orderBasketDtoList = this.getOrderBasketByUUID(uuid);

        for(OrderBasketDto orderBasketDto : orderBasketDtoList) {
            Integer productQuantity = orderBasketDto.getQuantity();
            Integer productId = orderBasketDto.getProductId();

            ProductEntity productEntity = productRepository.findById(productId);
            if(productEntity == null) {
                throw new RuntimeException("Product does not exist for id: " + productId);
            }

            if(productEntity.getQuantity() < productQuantity) {
                throw new RuntimeException("Product quantity is greeter than product stock");
            }
        }
    }
}
