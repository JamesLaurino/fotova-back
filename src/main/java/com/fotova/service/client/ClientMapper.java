package com.fotova.service.client;

import com.fotova.dto.address.AddressDto;
import com.fotova.dto.client.ClientAddressDto;
import com.fotova.dto.client.ClientCommentDto;
import com.fotova.dto.client.ClientDto;
import com.fotova.entity.AddressEntity;
import com.fotova.entity.ClientEntity;
import com.fotova.entity.CommentEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClientMapper {

    public List<ClientDto> maptoClientDtoList(List<ClientEntity> clientEntityList) {

        List<ClientDto> clientDtoList = new ArrayList<>();

        for (ClientEntity clientEntity : clientEntityList) {
            ClientDto clientDto = mapClientToClientDto(clientEntity);
            clientDtoList.add(clientDto);
        }

        return clientDtoList;
    }

    public ClientDto mapClientToClientDto(ClientEntity client) {

        ClientDto clientDto = new ClientDto();

        if(client.getId() != null) {
            clientDto.setId(client.getId());
        }

        clientDto.setUsername(client.getUsername());
        clientDto.setEmail(client.getEmail());
        clientDto.setIsActive(client.getIsActive());

        if(client.getAddress() != null) {

            ClientAddressDto clientAddressDto = new ClientAddressDto();
            clientAddressDto.setId(client.getAddress().getId());
            clientAddressDto.setStreet(client.getAddress().getStreet());
            clientAddressDto.setCity(client.getAddress().getCity());
            clientAddressDto.setNumber(client.getAddress().getNumber());
            clientAddressDto.setCountry(client.getAddress().getCountry());

            clientDto.setAddress(clientAddressDto);
        }

        if(client.getCommentEntities() != null) {

            List<ClientCommentDto> clientCommentDtoList = new ArrayList<>();

            for(CommentEntity commentEntity : client.getCommentEntities()) {
                ClientCommentDto clientCommentDto = new ClientCommentDto();
                clientCommentDto.setId(commentEntity.getId());
                clientCommentDto.setBody(commentEntity.getBody());
                clientCommentDto.setHeader(commentEntity.getHeader());
                clientCommentDto.setCreateAt(commentEntity.getCreateAt());
                clientCommentDto.setUpdateAt(commentEntity.getUpdateAt());
                clientCommentDtoList.add(clientCommentDto);
            }

            clientDto.setCommentEntities(clientCommentDtoList);
        }

        return clientDto;
    }

    // todo : remove ! already exist in addressMapper
    public AddressEntity mapAddressToAddressEntity(AddressDto addressDto) {

        AddressEntity addressEntity = new AddressEntity();

        if(addressDto.getId() != null)
        {
            addressEntity.setId(addressDto.getId());
        }

        addressEntity.setStreet(addressDto.getStreet());
        addressEntity.setCity(addressDto.getCity());
        addressEntity.setNumber(addressDto.getNumber());
        addressEntity.setCountry(addressDto.getCountry());

        return addressEntity;
    }

    public ClientEntity anonymization(ClientEntity clientEntity) {
        clientEntity.setUsername(UUID.randomUUID().toString());
        clientEntity.setEmail(UUID.randomUUID().toString());
        clientEntity.setPassword(UUID.randomUUID().toString());
        return clientEntity;
    }
}