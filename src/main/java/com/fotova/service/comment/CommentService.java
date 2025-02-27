package com.fotova.service.comment;

import com.fotova.dto.comment.CommentDto;
import com.fotova.entity.CommentEntity;
import com.fotova.repository.comment.CommentRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepositoryImpl commentRepositoryImpl;

    @Autowired
    private CommentMapper commentMapper;

    public List<CommentDto> getAllComments() {
        return commentMapper.mapToCommentDtoList(commentRepositoryImpl.findAll());
    }

    public CommentDto getCommentById(int commentId) {
        CommentEntity commentEntity = commentRepositoryImpl.findById(commentId);
        return commentMapper.mapToCommentDto(commentEntity);
    }
}
