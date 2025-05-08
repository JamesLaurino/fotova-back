package com.fotova.repository.comment;

import com.fotova.entity.CommentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepositoryJpa extends JpaRepository<CommentEntity, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE CommentEntity c SET c.clientEntity = null WHERE c.id = ?1")
    void updateCommentFromClient(Integer commentId);


    @Modifying
    @Transactional
    @Query(value = "UPDATE comment_entity SET client_id = :clientId WHERE id = :commentId", nativeQuery = true)
    void setCommentClientId(@Param("clientId") Integer clientId, @Param("commentId") Integer commentId);


}
