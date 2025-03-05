package com.fotova.repository.comment;

import com.fotova.entity.CommentEntity;
import com.fotova.repository.ICrud;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepositoryImpl implements ICrud<CommentEntity> {

    @Autowired
    private CommentRepositoryJpa commentRepositoryJpa;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CommentEntity findById(int id) {
        return commentRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    public List<CommentEntity> findAll() {
        return commentRepositoryJpa.findAll();
    }

    @Override
    public CommentEntity save(CommentEntity commentEntity) {
        return commentRepositoryJpa.save(commentEntity);
    }

    @Override
    public void deleteAll() {}

    @Override
    public void deleteById(int id) {
        commentRepositoryJpa.deleteById(id);
    }

    @Override
    public CommentEntity update(CommentEntity commentEntity) {
        return commentRepositoryJpa.save(commentEntity);
    }

    @Transactional
    public void setCommentClientId(Integer clientId, Integer commentId) {
        String sql = "UPDATE comment_entity SET client_id = ?1 WHERE ID = ?2";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, clientId);
        query.setParameter(2, commentId);
        query.executeUpdate();
    }

    @Transactional
    public void updateCommentClientId(Integer clientId) {
        String sql = "UPDATE comment_entity SET client_id = NULL WHERE client_id = ?1";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, clientId);
        query.executeUpdate();

    }

    @Transactional
    public void updateClientCommentId(Integer commentId) {
        String sql = "UPDATE client_entity SET comment_id = NULL WHERE comment_id = ?1";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, commentId);
        query.executeUpdate();

    }
}
