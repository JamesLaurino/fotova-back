package com.fotova.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ClientCommentDto {
    private Integer clientId;
    private String email;
    private String username;
}
