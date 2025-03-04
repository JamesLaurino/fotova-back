package com.fotova.dto.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ClientCommentDto {
    private Integer id;

    private String header;

    private String body;

    private Instant createAt;

    private Instant updateAt;
}
