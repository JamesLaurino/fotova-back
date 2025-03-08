package com.fotova.repository.redis;

import com.fotova.dto.order.OrderBasketDto;
import org.springframework.data.repository.CrudRepository;


public interface OrderBasketRepositoryRedis extends CrudRepository<OrderBasketDto, String> {
}