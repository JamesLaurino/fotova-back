package com.fotova.repository.client;

import com.fotova.entity.ClientEntity;
import com.fotova.repository.ICrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepositoryImpl implements ICrud<ClientEntity> {

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

    public void updateClientAddress(Integer clientId) {
        clientRepository.updateClientAddress(clientId);
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
