package com.fotova.repository.product;

import com.fotova.entity.CategoryEntity;
import com.fotova.entity.ProductEntity;
import com.fotova.repository.ICrud;
import com.fotova.repository.category.CategoryRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ICrud<ProductEntity> {

    @Autowired
    private ProductRepositoryJpa productRepositoryJpa;

    @Autowired
    private CategoryRepositoryJpa categoryRepositoryJpa;

    @Override
    @Transactional
    public ProductEntity findById(int id) {
        return productRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public List<ProductEntity> findAll() {
        return productRepositoryJpa.findAll();
    }

    @Override
    @Transactional
    public ProductEntity save(ProductEntity productEntity) {;
        return productRepositoryJpa.save(productEntity);
    }

    @Override
    @Transactional
    public void deleteAll() {}

    @Override
    @Transactional
    public ProductEntity update(ProductEntity productEntity) {
        return productRepositoryJpa.save(productEntity);
    }

    @Transactional
    public ProductEntity saveWithCategory(ProductEntity productEntity, int categoryId) {
        CategoryEntity categoryEntity = categoryRepositoryJpa.findById(categoryId).get();
        productEntity.setCategory(categoryEntity);
        return productRepositoryJpa.save(productEntity);
    }

    @Override
    public void deleteById(int id) {
        productRepositoryJpa.deleteById(id);
    }

}
