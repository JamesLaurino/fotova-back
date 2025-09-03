package com.fotova.service.category;

import com.fotova.dto.category.CategoryDto;
import com.fotova.entity.CategoryEntity;
import com.fotova.exception.DataExistException;
import com.fotova.exception.NotFoundException;
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
        if (categoryEntity == null) {
            throw new NotFoundException("Category not found for id: " + categoryId);
        }
        return categoryMapper.toCategoryDto(categoryEntity);
    }

    public List<CategoryDto> getAllCategories(){
        List<CategoryEntity> categoryEntities = categoryRepositoryImpl.findAll();
        return categoryMapper.toCategoryDtoList(categoryEntities);
    }

    public String deleteCategoryById(int id){
        try {
            categoryRepositoryImpl.updateProductCategoryId(id);
            categoryRepositoryImpl.deleteById(id);
            return "Category deleted successfully for id : " + id;
        } catch (Exception e) {
            throw new NotFoundException("Category not found for id: " + id);
        }
    }

    public CategoryDto addCategory(CategoryDto categoryDto){
        try {
            CategoryEntity categoryEntity = categoryMapper.toCategoryEntity(categoryDto);
            CategoryEntity categoryEntityRes = categoryRepositoryImpl.save(categoryEntity);
            return categoryMapper.toCategoryDto(categoryEntityRes);
        } catch (Exception e) {
            throw new DataExistException("Category already exists");
        }
    }

    public CategoryDto updateCategory(CategoryDto categoryDto){
        try {
            CategoryEntity categoryEntity = categoryMapper.toCategoryEntity(categoryDto);
            CategoryEntity categoryEntityRes = categoryRepositoryImpl.update(categoryEntity);
            return categoryMapper.toCategoryDto(categoryEntityRes);
        } catch (Exception e) {
            throw new NotFoundException("Category not found for id: " + categoryDto.getId());
        }
    }
}
