package com.fotova.dto.authentification.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@Getter @Setter
@RedisHash("RegisterRequestRedis")
public class RegisterRequestDto {

    private String id;
    private String registerId;
    private String username;
    private String email;
    private String password;
}