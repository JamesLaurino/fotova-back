package com.fotova.repository.supplier;

import com.fotova.entity.SupplierEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SupplierRepositoryJpa extends JpaRepository<SupplierEntity, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE SupplierEntity s SET s.product = null WHERE s.id = ?1")
    void updateSupplierProductId(Integer supplierID);

    @Modifying
    @Transactional
    @Query("UPDATE SupplierEntity s SET s.address = null WHERE s.id = ?1")
    void updateSupplierAddressId(Integer supplierID);
}
