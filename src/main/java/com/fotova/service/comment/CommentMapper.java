package com.fotova.service.comment;

import com.fotova.dto.comment.CommentClientDto;
import com.fotova.dto.comment.CommentDto;
import com.fotova.entity.CommentEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentMapper {

    public List<CommentDto> mapToCommentDtoList(List<CommentEntity> commentEntityList) {

        List<CommentDto> commentDtoList = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntityList) {

            CommentDto commentDto = mapToCommentDto(commentEntity);
            commentDtoList.add(commentDto);
        }

        return commentDtoList;
    }

    public CommentDto mapToCommentDto(CommentEntity commentEntity) {

        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentEntity.getId());
        commentDto.setBody(commentEntity.getBody());
        commentDto.setHeader(commentEntity.getHeader());
        commentDto.setCreateAt(commentEntity.getCreateAt());
        commentDto.setUpdateAt(commentEntity.getUpdateAt());

        if(commentEntity.getClientEntity() != null) {

            CommentClientDto clientCommentDto = new CommentClientDto();
            clientCommentDto.setClientId(commentEntity.getClientEntity().getId());
            clientCommentDto.setEmail(commentEntity.getClientEntity().getEmail());
            clientCommentDto.setUsername(commentEntity.getClientEntity().getUsername());
            commentDto.setClientCommentDto(clientCommentDto);

        } else {
            commentDto.setClientCommentDto(null);
        }

        return commentDto;
    }

    public CommentEntity mapToCommentEntity(CommentDto commentDto) {

        CommentEntity commentEntity = new CommentEntity();
        if(commentDto.getId() != null)
        {
            commentEntity.setId(commentDto.getId());
        } else {
            commentEntity.setId(null);
        }

        commentEntity.setHeader(commentDto.getHeader());
        commentEntity.setBody(commentDto.getBody());

        if(commentDto.getCreateAt() != null)
        {
            commentEntity.setCreateAt(commentDto.getCreateAt());
        }
        else {
            commentEntity.setCreateAt(null);
        }

        commentEntity.setUpdateAt(commentDto.getUpdateAt());
        return commentEntity;
    }
}