package com.fotova.service.order;

import com.fotova.dto.order.OrderDto;
import com.fotova.dto.stripe.StripeProductRequest;
import com.fotova.entity.OrderEntity;
import com.fotova.repository.order.OrderRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepositoryImpl orderRepository;

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
}
