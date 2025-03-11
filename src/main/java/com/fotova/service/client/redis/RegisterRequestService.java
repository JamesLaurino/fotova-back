package com.fotova.service.client.redis;

import com.fotova.dto.authentification.redis.RegisterRequestDto;
import com.fotova.dto.request.RegisterRequest;
import com.fotova.repository.redis.register.RegisterRequestRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterRequestService {

    @Autowired
    private RegisterRequestRepositoryImpl registerRequestRepository;

    @Autowired
    private RegisterRequestMapper registerRequestMapper;

    public List<RegisterRequestDto> findAll() {
        return registerRequestRepository.findAll();
    }

    public void deleteAll() {
        registerRequestRepository.deleteAll();
    }

    public RegisterRequestDto save(RegisterRequest registerRequest) {
        return registerRequestRepository
                .save(registerRequestMapper.mapToRegisterRequestDto(registerRequest));
    }

}