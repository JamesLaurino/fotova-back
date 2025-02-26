package com.fotova.dto.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AddressDto {
    private Integer id;
    private String street;
    private String city;
    private String number;
    private String country;
}
