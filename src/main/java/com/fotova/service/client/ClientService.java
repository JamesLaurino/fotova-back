package com.fotova.service.client;

import com.fotova.dto.client.ClientDto;
import com.fotova.entity.ClientEntity;
import com.fotova.repository.client.ClientRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepositoryImpl clientRepositoryImpl;

    @Autowired
    private ClientMapper clientMapper;

    public List<ClientDto> getAllClients() {
        return clientMapper.maptoClientDtoList(clientRepositoryImpl.findAll());
    }

    public ClientDto getClientById(Integer id) {
        ClientEntity clientEntity = clientRepositoryImpl.findById(id);
        return clientMapper.mapClientToClientDto(clientEntity);
    }

}