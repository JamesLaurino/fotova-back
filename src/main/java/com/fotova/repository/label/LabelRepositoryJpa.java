package com.fotova.repository.label;

import com.fotova.entity.LabelEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LabelRepositoryJpa extends JpaRepository<LabelEntity,Integer> {
    @Transactional
    @Query(value = "SELECT l FROM LabelEntity l WHERE l.product.id = ?1")
    LabelEntity findLabelByProductId(Integer productId);
}
