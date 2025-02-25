package com.fotova.repository.category;

import com.fotova.entity.CategoryEntity;
import com.fotova.repository.ICrud;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements ICrud<CategoryEntity> {

    @Autowired
    private CategoryRepositoryJpa categoryRepositoryJpa;

    @PersistenceContext
    private EntityManager entityManager;

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
        return categoryRepositoryJpa.save(categoryEntity);
    }

    @Override
    public void deleteAll() {}

    @Override
    public void deleteById(int id) {
        categoryRepositoryJpa.deleteById(id);
    }

    @Override
    public CategoryEntity update(CategoryEntity categoryEntity) {
        return categoryRepositoryJpa.save(categoryEntity);
    }

    @Transactional
    public void updateProductCategoryId(Integer categoryId) {
        String sql = "UPDATE product_entity SET category_id = NULL WHERE category_id = ?1";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, categoryId);
        query.executeUpdate();

    }

}
