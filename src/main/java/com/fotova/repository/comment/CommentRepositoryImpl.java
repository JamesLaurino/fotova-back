package com.fotova.repository.comment;

import com.fotova.entity.CommentEntity;
import com.fotova.repository.ICrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepositoryImpl implements ICrud<CommentEntity> {

    @Autowired
    private CommentRepositoryJpa commentRepositoryJpa;

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
        return null;
    }

    @Override
    public void deleteAll() {}

    @Override
    public void deleteById(int id) {}

    @Override
    public CommentEntity update(CommentEntity commentEntity) {
        return commentRepositoryJpa.save(commentEntity);
    }
}
