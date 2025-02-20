package com.fotova.service.category;

import com.fotova.entity.CategoryEntity;
import com.fotova.repository.category.CategoryRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepositoryJpa categoryRepositoryJpa;

    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryEntity getCategoryById(int categoryId){
        return categoryRepositoryJpa.findById(categoryId).get();
    }
}
