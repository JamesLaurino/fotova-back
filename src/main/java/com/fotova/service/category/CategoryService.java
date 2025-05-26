package com.fotova.service.category;

import com.fotova.dto.category.CategoryDto;
import com.fotova.entity.CategoryEntity;
import com.fotova.repository.category.CategoryRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepositoryImpl categoryRepositoryImpl;

    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryEntity getCategoryById(int categoryId){
        return categoryRepositoryImpl.findById(categoryId);
    }

    public CategoryDto getCategoryDtoById(int categoryId){
        CategoryEntity categoryEntity = categoryRepositoryImpl.findById(categoryId);
        return categoryMapper.toCategoryDto(categoryEntity);
    }

    public List<CategoryDto> getAllCategories(){
        List<CategoryEntity> categoryEntities = categoryRepositoryImpl.findAll();
        return categoryMapper.toCategoryDtoList(categoryEntities);
    }

    public String deleteCategoryById(int id){
        categoryRepositoryImpl.updateProductCategoryId(id);
        categoryRepositoryImpl.deleteById(id);
        return "Category deleted successfully for id : " + id;
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
