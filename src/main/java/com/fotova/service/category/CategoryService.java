package com.fotova.service.category;

import com.fotova.dto.category.CategoryDto;
import com.fotova.entity.CategoryEntity;
import com.fotova.repository.category.CategoryRepositoryImpl;
import com.fotova.repository.category.CategoryRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepositoryJpa categoryRepositoryJpa;

    @Autowired
    private CategoryRepositoryImpl categoryRepositoryImpl;

    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryEntity getCategoryById(int categoryId){
        return categoryRepositoryJpa.findById(categoryId).get();
    }

    public List<CategoryDto> getAllCategories(){
        List<CategoryEntity> categoryEntities = categoryRepositoryImpl.findAll();
        return categoryMapper.toCategoryDtoList(categoryEntities);
    }

    public CategoryDto addCategory(CategoryDto categoryDto){
        CategoryEntity categoryEntity = categoryMapper.toCategoryEntity(categoryDto);
        CategoryEntity categoryEntityRes = categoryRepositoryImpl.save(categoryEntity);
        return categoryMapper.toCategoryDto(categoryEntityRes);
    }

    public CategoryDto updateCategory(CategoryDto categoryDto){
        CategoryEntity categoryEntity = categoryMapper.toCategoryEntity(categoryDto);
        CategoryEntity categoryEntityRes = categoryRepositoryImpl.update(categoryEntity);
        return categoryMapper.toCategoryDto(categoryEntityRes);
    }
}
