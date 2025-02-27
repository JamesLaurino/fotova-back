package com.fotova.repository.comment;

import com.fotova.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepositoryJpa extends JpaRepository<CommentEntity, Integer> {
}
