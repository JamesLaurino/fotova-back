package com.fotova.repository.address;

import com.fotova.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepositoryJpa extends JpaRepository<AddressEntity,Integer> {
}
