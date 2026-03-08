package com.fotova.repository.label;

import com.fotova.entity.LabelEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LabelRepositoryJpa extends JpaRepository<LabelEntity,Integer> {
    @Transactional
    @Query(value = "SELECT l FROM LabelEntity l WHERE l.product.id = ?1")
    LabelEntity findLabelByProductId(Integer productId);

    @Modifying
    @Transactional
    @Query("UPDATE LabelEntity l SET l.product.id = null WHERE l.product.id = ?1")
    void updateLabelByProductId(Integer productId);

    @Modifying
    @Transactional
    @Query("DELETE FROM LabelEntity l WHERE l.product.id = ?1")
    void deleteByProductId(Integer productId);
}
