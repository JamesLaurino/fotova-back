package com.fotova.repository.role;

import com.fotova.entity.RoleEntity;
import com.fotova.repository.ICrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepositoryImpl implements ICrud<RoleEntity> {

    @Autowired
    private RoleRepositoryJpa roleRepositoryJpa;

    @Override
    public RoleEntity findById(int id) {
        return roleRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    public List<RoleEntity> findAll() {
        return roleRepositoryJpa.findAll();
    }

    @Override
    public RoleEntity save(RoleEntity roleEntity) {
        return roleRepositoryJpa.save(roleEntity);
    }

    @Override
    public void deleteAll() {
    }

    @Override
    public RoleEntity update(RoleEntity roleEntity) {
        return null;
    }

    @Override
    public void deleteById(int id) {}
}
