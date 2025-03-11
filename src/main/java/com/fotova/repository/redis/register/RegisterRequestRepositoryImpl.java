package com.fotova.repository.redis.register;

import com.fotova.dto.authentification.redis.RegisterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegisterRequestRepositoryImpl {
    @Autowired
    private RegisterRequestRepositoryRedis registerRequestRepositoryRedis;

    public RegisterRequestDto save(RegisterRequestDto registerRequestDto) {
        return registerRequestRepositoryRedis.save(registerRequestDto);
    }

    public void deleteAll() {
        registerRequestRepositoryRedis.deleteAll();
    }

    public List<RegisterRequestDto> findAll() {
        return (List<RegisterRequestDto>) registerRequestRepositoryRedis.findAll();
    }

}