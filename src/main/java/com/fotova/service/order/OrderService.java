package com.fotova.service.order;

import com.fotova.dto.order.OrderDto;
import com.fotova.dto.stripe.StripeProductRequest;
import com.fotova.entity.ClientEntity;
import com.fotova.entity.OrderEntity;
import com.fotova.repository.client.ClientRepositoryImpl;
import com.fotova.repository.order.OrderRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepositoryImpl orderRepository;

    @Autowired
    private ClientRepositoryImpl clientRepository;

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
}
