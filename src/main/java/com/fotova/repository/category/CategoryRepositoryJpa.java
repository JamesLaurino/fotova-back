package com.fotova.repository.category;

import com.fotova.entity.CategoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepositoryJpa extends JpaRepository<CategoryEntity,Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE ProductEntity p SET p.category = null WHERE p.category.id = ?1")
    void updateProductCategoryId(Integer clientAddressId);
}
