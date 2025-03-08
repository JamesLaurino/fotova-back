package com.fotova.repository.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class OrderBasketRepositoryImpl {
    @Autowired
    private OrderBasketRepositoryRedis orderBasketRepositoryRedis;
}