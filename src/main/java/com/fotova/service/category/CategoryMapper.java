package com.fotova.service.category;

import com.fotova.dto.category.CategoryDto;
import com.fotova.entity.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryMapper {
    public CategoryDto toCategoryDto(CategoryEntity categoryEntity){

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryEntity.getId());
        categoryDto.setName(categoryEntity.getName());

        return categoryDto;
    }

    public CategoryEntity toCategoryEntity(CategoryDto categoryDto){
        CategoryEntity categoryEntity = new CategoryEntity();
        if(categoryDto.getId()!=null){
            categoryEntity.setId(categoryDto.getId());
        }
        categoryEntity.setName(categoryDto.getName());
        return categoryEntity;
    }

    public List<CategoryDto> toCategoryDtoList(List<CategoryEntity> categoryEntities){
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for(CategoryEntity categoryEntity : categoryEntities){
            CategoryDto categoryDto = toCategoryDto(categoryEntity);
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }
}