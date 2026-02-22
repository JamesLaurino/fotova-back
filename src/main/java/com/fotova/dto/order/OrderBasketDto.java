package com.fotova.dto.order;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;


@Getter
@Setter
@RedisHash("OrderBasketRequestRedis")
public class OrderBasketDto {
    private String id;
    private String verificationCode;
    private Integer productId;
    private Integer quantity;
    private String email;
}
