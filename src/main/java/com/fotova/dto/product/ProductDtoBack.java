package com.fotova.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ProductDtoBack {

    private Integer id;
    private String name;
    @Min(value = 0, message = "Quantity must be greater or equal than 0")
    private Integer quantity;
    @Min(value = 0, message = "Price must be greater or equal than 0")
    private Double price;
    private CategoryInnerProductDto categoryInnerProductDto;
    private String url;
    private List<String> images;
}