package com.fotova.repository.product;

import com.fotova.entity.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepositoryJpa extends JpaRepository<ProductEntity,Integer> {
    @Transactional
    @Query(value = "SELECT id, path FROM image_entity WHERE product_id = ?1", nativeQuery = true)
    List<Object[]> getProductImages(Integer productId);

    @Transactional
    @Query(value = "SELECT p FROM ProductEntity p WHERE p.category.id = ?1")
    List<ProductEntity> findProductsByCategoryId(Integer categoryId);
}
