package com.fotova.service.comment;

import com.fotova.dto.comment.CommentDto;
import com.fotova.entity.CommentEntity;
import com.fotova.exception.DataExistException;
import com.fotova.exception.NotFoundException;
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

    public CommentDto saveComment(CommentDto commentDto) {
        try {
            CommentEntity commentEntity = commentMapper.mapToCommentEntity(commentDto);
            commentEntity = commentRepositoryImpl.save(commentEntity);
            return commentMapper.mapToCommentDto(commentEntity);
        } catch (Exception e) {
            throw new DataExistException("Comment already exists");
        }
    }

    public String deleteCommentById(int id) {
        try {
            commentRepositoryImpl.updateCommentClientId(id);
            commentRepositoryImpl.deleteById(id);
            return "Comment has been deleted successfully for id : " + id;
        } catch (Exception e) {
            throw new NotFoundException("Comment not found for id: " + id);
        }
    }

    public CommentDto updateComment(CommentDto commentDto) {
        try {
            CommentEntity commentEntity = commentRepositoryImpl.update(commentMapper.mapToCommentEntity(commentDto));
            return commentMapper.mapToCommentDto(commentEntity);
        } catch (Exception e) {
            throw new NotFoundException("Comment not found for id: " + commentDto.getId());
        }
    }

    public CommentDto getCommentById(int commentId) {
        CommentEntity commentEntity = commentRepositoryImpl.findById(commentId);
        if (commentEntity == null) {
            throw new NotFoundException("Comment not found for id: " + commentId);
        }
        return commentMapper.mapToCommentDto(commentEntity);
    }
}
