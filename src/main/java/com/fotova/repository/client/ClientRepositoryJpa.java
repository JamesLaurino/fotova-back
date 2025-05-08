package com.fotova.repository.client;

import com.fotova.entity.ClientEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepositoryJpa extends JpaRepository<ClientEntity, Integer> {
    Optional<ClientEntity> findByUsername(String username);

    Optional<ClientEntity> findFirstByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);


    @Modifying
    @Transactional
    @Query("UPDATE ClientEntity c SET c.address = null WHERE c.id = ?1")
    void updateClientAddress(Integer clientAddressId);


}
