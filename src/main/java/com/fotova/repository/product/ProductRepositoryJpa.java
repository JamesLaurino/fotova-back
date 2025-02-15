package com.fotova.repository.product;

import com.fotova.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryJpa extends JpaRepository<ProductEntity,Integer> {
}
