package com.fotova.repository.redis.resetPassword;

import com.fotova.dto.authentification.redis.ResetPasswordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResetPasswordRepositoryImpl {
    @Autowired
    private ResetPasswordRepositoryRedis resetPasswordRepositoryRedis;

    public List<ResetPasswordDto> findAll() {
        return (List<ResetPasswordDto>) resetPasswordRepositoryRedis.findAll();
    }

    public ResetPasswordDto save(ResetPasswordDto resetPasswordDto) {
        return resetPasswordRepositoryRedis.save(resetPasswordDto);
    }

    public void deleteAll() {
        resetPasswordRepositoryRedis.deleteAll();
    }

    public void deleteById(String id) {
        resetPasswordRepositoryRedis.deleteById(id);
    }

    public ResetPasswordDto findByToken(String token) {
        List<ResetPasswordDto> resetPasswordDtoList = (List<ResetPasswordDto>) resetPasswordRepositoryRedis.findAll();
        return  resetPasswordDtoList.stream().filter(resetPasswordDto ->
                resetPasswordDto.getToken().equals(token)).findFirst().orElse(null);
    }

    public ResetPasswordDto findById(String id) {
        return (ResetPasswordDto) resetPasswordRepositoryRedis.findById(id).orElse(null);
    }
}
