package com.fotova.dto.ai;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AiProductOutputDto {
    private String description_english;
    private String description_russian;
    private String description_french;

    private String title_english;
    private String title_russian;
    private String title_french;

}
