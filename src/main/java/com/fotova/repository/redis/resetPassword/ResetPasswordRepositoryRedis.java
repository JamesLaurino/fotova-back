package com.fotova.repository.redis.resetPassword;

import com.fotova.dto.authentification.redis.ResetPasswordDto;
import org.springframework.data.repository.CrudRepository;

public interface ResetPasswordRepositoryRedis extends CrudRepository<ResetPasswordDto, String> {
}
