package com.fotova.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class SupplierAddressDto {
    private Integer id;
    private String street;
    private String city;
    private String number;
    private String country;
}