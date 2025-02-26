package com.fotova.repository.address;

import com.fotova.entity.AddressEntity;
import com.fotova.repository.ICrud;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressRepositoryImpl implements ICrud<AddressEntity> {

    @Autowired
    private AddressRepositoryJpa addressRepositoryJpa;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public AddressEntity findById(int id) {
        return addressRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    public List<AddressEntity> findAll() {
        return addressRepositoryJpa.findAll();
    }

    @Override
    public AddressEntity save(AddressEntity addressEntity) {
        return addressRepositoryJpa.save(addressEntity);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public AddressEntity update(AddressEntity addressEntity) {
        return null;
    }
}
