package com.fotova.repository.role;

import com.fotova.entity.RoleEntity;
import com.fotova.repository.ICrud;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepositoryImpl implements ICrud<RoleEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public RoleEntity findById(int id) {
        return null;
    }

    @Override
    public List<RoleEntity> findAll() {
        return null;
    }

    @Override
    public RoleEntity save(RoleEntity roleEntity) {
        return null;
    }

    @Override
    public void delete(RoleEntity roleEntity) {
    }

    @Override
    public RoleEntity update(RoleEntity roleEntity) {
        return null;
    }
}
