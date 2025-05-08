package com.fotova.repository.address;

import com.fotova.entity.AddressEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepositoryJpa extends JpaRepository<AddressEntity,Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE ClientEntity c SET c.address = null WHERE c.address.id = ?1")
    void updateClientAddressId(Integer clientAddressId);

}
