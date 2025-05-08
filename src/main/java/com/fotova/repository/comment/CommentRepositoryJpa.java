package com.fotova.repository.comment;

import com.fotova.entity.CommentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepositoryJpa extends JpaRepository<CommentEntity, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE CommentEntity c SET c.clientEntity = null WHERE c.id = ?1")
    void updateCommentFromClient(Integer commentId);

    @Modifying
    @Transactional
    @Query("UPDATE CommentEntity c SET c.clientEntity.id = ?1 WHERE c.id = ?2")
    void setCommentClientId(Integer clientId, Integer commentId);

}
