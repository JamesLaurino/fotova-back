package com.fotova.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CommentDto {
    private int id;
    private String header;
    private String body;
    private Instant createAt;
    private ClientCommentDto clientCommentDto;
}
