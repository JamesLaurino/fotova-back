package com.fotova.repository.client;

import com.fotova.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepositoryJpa extends JpaRepository<ClientEntity, Integer> {
    Optional<ClientEntity> findByUsername(String username);

    Optional<ClientEntity> findFirstByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);
}
