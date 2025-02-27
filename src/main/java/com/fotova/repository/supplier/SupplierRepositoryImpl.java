package com.fotova.repository.supplier;

import com.fotova.entity.SupplierEntity;
import com.fotova.repository.ICrud;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void updateSupplierAddressId(Integer supplierId) {

        String sql = "UPDATE supplier_entity SET address_id = NULL WHERE id = ?1";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, supplierId);
        query.executeUpdate();
    }

    @Transactional
    public void updateSupplierProductId(Integer supplierId) {

        String sql = "UPDATE supplier_entity SET product_id = NULL WHERE id = ?1";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, supplierId);
        query.executeUpdate();
    }

}
