package com.fotova.repository.client;

import com.fotova.entity.ClientEntity;
import com.fotova.repository.ICrud;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepositoryImpl implements ICrud<ClientEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClientRepositoryJpa clientRepository;

    @Override
    public ClientEntity findById(int id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public List<ClientEntity> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public ClientEntity save(ClientEntity clientEntity) {
        return clientRepository.save(clientEntity);
    }

    @Override
    public void delete(ClientEntity clientEntity) {
        clientRepository.delete(clientEntity);
    }

    @Override
    public ClientEntity update(ClientEntity clientEntity) {
        return clientRepository.save(clientEntity);
    }


    public Boolean existsByUsername(String username) {
        return clientRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

    public Optional<ClientEntity> findFirstByEmail(String email) {
        return clientRepository.findFirstByEmail(email);
    }

}
