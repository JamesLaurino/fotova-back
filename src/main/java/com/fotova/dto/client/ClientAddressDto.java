package com.fotova.dto.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ClientAddressDto {
    private Integer id;
    private String street;
    private String city;
    private String number;
    private String country;
}
