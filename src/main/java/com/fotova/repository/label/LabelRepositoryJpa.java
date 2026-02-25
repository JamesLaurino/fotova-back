package com.fotova.repository.label;

import com.fotova.entity.LabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepositoryJpa extends JpaRepository<LabelEntity,Integer> {
}
