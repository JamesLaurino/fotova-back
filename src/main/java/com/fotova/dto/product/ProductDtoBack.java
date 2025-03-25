package com.fotova.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ProductDtoBack {

    private Integer id;
    private String name;
    private Integer quantity;
    private Double price;
    private CategoryInnerProductDto categoryInnerProductDto;
    private String url;
}