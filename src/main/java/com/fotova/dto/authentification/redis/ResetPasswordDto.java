package com.fotova.dto.authentification.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@RedisHash("PasswordResetRedis")
public class ResetPasswordDto {
    private String id;
    private String token;
    private String email;
    private String password;
}
