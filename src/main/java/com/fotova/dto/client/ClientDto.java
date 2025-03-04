package com.fotova.dto.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ClientDto {
    private Integer id;
    private String username;
    private String email;
    private Boolean isActive;
    private ClientAddressDto address;
    private List<ClientCommentDto> commentEntities;
}
