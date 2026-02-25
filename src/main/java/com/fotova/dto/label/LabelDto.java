package com.fotova.dto.label;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabelDto {

    private Integer id;

    private String descriptionEn;
    private String descriptionRu;
    private String descriptionFr;

    private String titleEn;
    private String titleRu;
    private String titleFr;

    private Integer productId;
}
