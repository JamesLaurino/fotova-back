package com.fotova.repository.client;

import com.fotova.entity.ClientEntity;
import com.fotova.repository.ICrud;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepositoryImpl implements ICrud<ClientEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ClientEntity findById(int id) {
        return null;
    }

    public Optional<ClientEntity> findFirstByEmail(String email) {
        return null;
    }

    @Override
    public List<ClientEntity> findAll() {
        return null;
    }

    @Override
    public ClientEntity save(ClientEntity clientEntity) {
        return null;
    }

    @Override
    public void delete(ClientEntity clientEntity) {

    }

    @Override
    public ClientEntity update(ClientEntity clientEntity) {
        return null;
    }

    public Boolean existsByUsername(String username) {
        //todo replace this with real logic
        return false;
    }

    public Boolean existsByEmail(String email) {
        //todo replace with real logic
        return false;
    }

}
