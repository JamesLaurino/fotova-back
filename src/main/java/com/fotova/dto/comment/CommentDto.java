package com.fotova.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CommentDto {
    private Integer id;
    @NotBlank(message = "Product cannot be null")
    private String header;
    @NotBlank(message = "Body cannot be null")
    private String body;
    private Instant createAt;
    private Instant updateAt;
    private CommentClientDto clientCommentDto;
}
