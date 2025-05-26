package com.fotova.repository.image;

import com.fotova.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepositoryJpa extends JpaRepository<ImageEntity,Integer> {
}
