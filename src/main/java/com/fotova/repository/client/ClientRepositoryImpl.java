package com.fotova.repository.client;

import com.fotova.entity.ClientEntity;
import com.fotova.repository.ICrud;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
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
    public void deleteAll() {
    }

    @Override
    public ClientEntity update(ClientEntity clientEntity) {
        return clientRepository.save(clientEntity);
    }

    @Override
    public void deleteById(int id) {}

    public ClientEntity clientAnonymization(Integer id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updateClientAddress(Integer clientId) {
        String sql = "UPDATE client_entity SET address_id = NULL WHERE ID = ?1";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, clientId);
        query.executeUpdate();
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
