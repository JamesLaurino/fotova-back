package com.fotova.repository.supplier;

import com.fotova.entity.SupplierEntity;
import com.fotova.repository.ICrud;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SupplierRepositoryImpl implements ICrud<SupplierEntity> {

    @Autowired
    private SupplierRepositoryJpa supplierRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SupplierEntity findById(int id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    public List<SupplierEntity> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public SupplierEntity save(SupplierEntity supplierEntity) {
        return supplierRepository.save(supplierEntity);
    }

    @Override
    public void deleteAll() {}

    @Override
    public void deleteById(int id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public SupplierEntity update(SupplierEntity supplierEntity) {
        return supplierRepository.save(supplierEntity);
    }


    public void updateSupplierAddressId(Integer supplierId) {
        supplierRepository.updateSupplierAddressId(supplierId);
    }

    public void updateSupplierProductId(Integer supplierId) {
        supplierRepository.updateSupplierProductId(supplierId);
    }

}
