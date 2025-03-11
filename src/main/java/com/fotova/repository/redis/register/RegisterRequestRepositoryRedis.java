package com.fotova.repository.redis.register;

import com.fotova.dto.authentification.redis.RegisterRequestDto;
import org.springframework.data.repository.CrudRepository;

public interface RegisterRequestRepositoryRedis extends CrudRepository<RegisterRequestDto, String> {
}
