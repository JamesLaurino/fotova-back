package com.fotova.dto.image;

import com.fotova.dto.product.ProductDtoBack;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {
    private Integer id;
    private String path;
    private ProductDtoBack productDtoBack;
}