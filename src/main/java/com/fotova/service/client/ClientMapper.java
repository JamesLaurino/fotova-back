package com.fotova.service.client;

import com.fotova.dto.client.ClientAddressDto;
import com.fotova.dto.client.ClientCommentDto;
import com.fotova.dto.client.ClientDto;
import com.fotova.entity.ClientEntity;
import com.fotova.entity.CommentEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

}