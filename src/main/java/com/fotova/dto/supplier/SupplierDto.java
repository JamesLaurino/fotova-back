package com.fotova.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class SupplierDto {
    private Integer id;
    private String registrationNumber;
    private SupplierAddressDto supplierAddressDto;
    private SupplierProductDto supplierProductDto;
}