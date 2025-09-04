package com.fotova.dto.image;

import com.fotova.dto.product.ProductDtoBack;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {
    private Integer id;
    @NotBlank(message = "Path cannot be empty")
    private String path;
    private ProductDtoBack productDtoBack;
}