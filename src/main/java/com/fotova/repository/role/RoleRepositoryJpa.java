package com.fotova.repository.role;

import com.fotova.entity.ERole;
import com.fotova.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface RoleRepositoryJpa extends JpaRepository<RoleEntity,Integer> {
    Optional<RoleEntity> findByName(ERole name);
}
