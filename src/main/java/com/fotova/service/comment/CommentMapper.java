package com.fotova.service.comment;

import com.fotova.dto.comment.ClientCommentDto;
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

        if(commentEntity.getClientEntity() != null) {

            ClientCommentDto clientCommentDto = new ClientCommentDto();
            clientCommentDto.setClientId(commentEntity.getClientEntity().getId());
            clientCommentDto.setEmail(commentEntity.getClientEntity().getEmail());
            clientCommentDto.setUsername(commentEntity.getClientEntity().getUsername());
            commentDto.setClientCommentDto(clientCommentDto);

        } else {
            commentDto.setClientCommentDto(null);
        }

        return commentDto;
    }
}
