package com.fotova.service.client.redis;

import com.fotova.dto.authentification.redis.RegisterRequestDto;
import com.fotova.dto.request.RegisterRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterRequestMapper {
    public RegisterRequestDto mapToRegisterRequestDto(RegisterRequest registerRequest) {
        String uuid = UUID.randomUUID().toString();
        RegisterRequestDto registerRequestDto = new RegisterRequestDto();
        registerRequestDto.setUsername(registerRequest.getUsername());
        registerRequestDto.setPassword(registerRequest.getPassword());
        registerRequestDto.setEmail(registerRequest.getEmail());
        registerRequestDto.setRegisterId(uuid);
        return registerRequestDto;
    }
}
