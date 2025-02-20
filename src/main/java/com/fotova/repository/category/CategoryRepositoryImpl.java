package com.fotova.repository.category;

import com.fotova.entity.CategoryEntity;
import com.fotova.repository.ICrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements ICrud<CategoryEntity> {

    @Autowired
    private CategoryRepositoryJpa categoryRepositoryJpa;

    @Override
    public CategoryEntity findById(int id) {
        return categoryRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepositoryJpa.findAll();
    }

    @Override
    public CategoryEntity save(CategoryEntity categoryEntity) {
        return null;
    }

    @Override
    public void deleteAll() {}

    @Override
    public void deleteById(int id) {}

    @Override
    public CategoryEntity update(CategoryEntity categoryEntity) {
        return null;
    }
}
