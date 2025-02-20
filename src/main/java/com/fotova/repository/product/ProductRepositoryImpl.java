package com.fotova.repository.product;

import com.fotova.dto.category.CategoryDto;
import com.fotova.entity.CategoryEntity;
import com.fotova.entity.ProductEntity;
import com.fotova.repository.ICrud;
import com.fotova.service.category.CategoryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ICrud<ProductEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductRepositoryJpa productRepositoryJpa;

    @Autowired
    private CategoryService categoryService;

    @Override
    @Transactional
    public ProductEntity findById(int id) {
//        String sql = "SELECT * FROM product_entity WHERE id = ?1";
//
//        Query query = entityManager.createNativeQuery(sql);
//        query.setParameter(1, id);
//
//        Object[] row = (Object[]) query.getSingleResult();  // Une seule ligne retournée
//
//        ProductEntity entity = new ProductEntity();
//        entity.setId((int)row[1]);
//        entity.setName((String) row[4]);
//        entity.setPrice((Double) row[2]);
//        entity.setQuantity((int) row[3]);
//        entity.setCategory(null);
//
//        return entity;
        return productRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public List<ProductEntity> findAll() {
        return productRepositoryJpa.findAll();
//        String sql = "SELECT * FROM product_entity";
//
//        Query query = entityManager.createNativeQuery(sql);
//
//        List<Object[]> results = query.getResultList();
//        List<ProductEntity> productEntities = new ArrayList<>();
//
//        for (Object[] row : results) {
//            ProductEntity entity = new ProductEntity();
//            entity.setId((int)row[1]);
//            entity.setName((String) row[4]);
//            entity.setPrice((Double) row[2]);
//            entity.setQuantity((int) row[3]);
//            entity.setCategory(null);
//            productEntities.add(entity);
//        }
//
//        return productEntities;
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
        return entityManager.merge(productEntity);
    }


    @Transactional
    public ProductEntity saveWithCategory(ProductEntity productEntity, int categoryId) {

        CategoryEntity categoryEntity = categoryService.getCategoryById(categoryId);
        productEntity.setCategory(categoryEntity);
        return productRepositoryJpa.save(productEntity);
    }

    @Override
    public void deleteById(int id) {
        productRepositoryJpa.deleteById(id);
    }

}
