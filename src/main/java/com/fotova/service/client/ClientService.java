package com.fotova.service.client;

import com.fotova.dto.address.AddressDto;
import com.fotova.dto.client.ClientDto;
import com.fotova.entity.AddressEntity;
import com.fotova.entity.ClientEntity;
import com.fotova.repository.address.AddressRepositoryImpl;
import com.fotova.repository.client.ClientRepositoryImpl;
import com.fotova.service.address.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepositoryImpl clientRepositoryImpl;

    @Autowired
    private AddressRepositoryImpl addressRepositoryImpl;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private AddressMapper addressMapper;

    public List<ClientDto> getAllClients() {
        return clientMapper.maptoClientDtoList(clientRepositoryImpl.findAll());
    }

    public ClientDto getClientById(Integer id) {
        ClientEntity clientEntity = clientRepositoryImpl.findById(id);
        return clientMapper.mapClientToClientDto(clientEntity);
    }

    public ClientDto updateAddressClient(Integer clientId,AddressDto addressDto) {

        ClientEntity clientEntity = clientRepositoryImpl.findById(clientId);

        if(clientEntity !=null && clientEntity.getAddress() != null){
            AddressEntity addressEntity = clientMapper.mapAddressToAddressEntity(addressDto);
            addressEntity.setId(clientEntity.getAddress().getId());
            addressRepositoryImpl.save(addressEntity);
            return clientMapper.mapClientToClientDto(clientRepositoryImpl.findById(clientId));
        }

        return null;
    }

    public ClientDto addAddressClient(Integer clientId,AddressDto addressDto) {

        AddressEntity addressEntity = addressMapper.mapToAddressEntity(addressDto);
        addressEntity = addressRepositoryImpl.save(addressEntity);

        ClientEntity clientEntity = clientRepositoryImpl.findById(clientId);
        clientEntity.setAddress(addressEntity);
        clientRepositoryImpl.save(clientEntity);

        return clientMapper.mapClientToClientDto(clientRepositoryImpl.findById(clientId));
    }
}