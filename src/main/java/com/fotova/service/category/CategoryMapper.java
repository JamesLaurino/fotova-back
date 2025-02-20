package com.fotova.service.category;

import com.fotova.dto.category.CategoryDto;
import com.fotova.entity.CategoryEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    public CategoryDto toCategoryDto(CategoryEntity categoryEntity){

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryEntity.getId());
        categoryDto.setName(categoryEntity.getName());

        return categoryDto;
    }
}