package com.fotova.dto.address;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AddressDto {
    private Integer id;
    @NotBlank(message = "Street cannot be empty")
    private String street;
    @NotBlank(message = "City cannot be empty")
    private String city;
    @NotBlank(message = "Number cannot be empty")
    private String number;
    @NotBlank(message = "Country cannot be empty")
    private String country;
}
