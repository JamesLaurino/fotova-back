package com.fotova.repository.supplier;

import com.fotova.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepositoryJpa extends JpaRepository<SupplierEntity, Integer> {
}
