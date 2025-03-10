package com.fotova.repository.redis;

import com.fotova.dto.order.OrderBasketDto;
import com.fotova.dto.stripe.StripeProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class OrderBasketRepositoryImpl {
    @Autowired
    private OrderBasketRepositoryRedis orderBasketRepositoryRedis;

    public List<OrderBasketDto> getAllOrderBasket() {
        return (List<OrderBasketDto>) orderBasketRepositoryRedis.findAll();
    }

    public void saveAllOrderBasket(List<OrderBasketDto> orderBasketDtoList) {
        orderBasketRepositoryRedis.saveAll(orderBasketDtoList);
    }

    public void deleteAllOrderBasket() {
        orderBasketRepositoryRedis.deleteAll();
    }

    public void deleteOrderBasket(OrderBasketDto orderBasketDto) {
        orderBasketRepositoryRedis.delete(orderBasketDto);
    }

    public OrderBasketDto addOrderBasketDto(OrderBasketDto orderBasketDto) {
        return orderBasketRepositoryRedis.save(orderBasketDto);
    }

}