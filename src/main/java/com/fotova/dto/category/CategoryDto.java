package com.fotova.dto.category;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CategoryDto
{
    private Integer id;
    @NotBlank(message = "Name cannot be empty")
    private String name;
}
