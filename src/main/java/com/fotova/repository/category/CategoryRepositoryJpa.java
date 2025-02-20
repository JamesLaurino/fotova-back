package com.fotova.repository.category;

import com.fotova.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositoryJpa extends JpaRepository<CategoryEntity,Integer> {
}
