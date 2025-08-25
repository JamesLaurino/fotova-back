package com.fotova.repository.image;

import com.fotova.entity.ImageEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepositoryJpa extends JpaRepository<ImageEntity,Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE ImageEntity i SET i.product.id = null WHERE i.product.id = ?1")
    void updateImagesByProductId(Integer productId);
}
