package com.fotova.dto.supplier;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class SupplierDto {
    private Integer id;
    @NotBlank(message = "Registration number cannot be null")
    private String registrationNumber;
    private SupplierAddressDto supplierAddressDto;
    private SupplierProductDto supplierProductDto;
}