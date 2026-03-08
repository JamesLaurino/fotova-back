package com.fotova.service.ai;

import com.fotova.dto.ai.AiProductInputDto;
import com.fotova.dto.ai.AiProductOutputDto;
import com.fotova.entity.LabelEntity;
import com.fotova.entity.ProductEntity;
import org.springframework.stereotype.Service;

@Service
public class AiMapper {
    public AiProductInputDto mapToAiProductInput(ProductEntity product) {
        AiProductInputDto aiProductInputDto = new AiProductInputDto();
        aiProductInputDto.setTitle(product.getName());
        aiProductInputDto.setDescription(product.getDescription());
        return aiProductInputDto;
    }

    private String safe(String val) {
        return (val == null || val.isBlank()) ? "N/A" : val;
    }

    public LabelEntity mapToLabelEntity( AiProductOutputDto aiProductOutputDto) {
        LabelEntity labelEntity = new LabelEntity();

        // TITLE
        labelEntity.setTitleEn(safe(aiProductOutputDto.getTitle_english()));
        labelEntity.setTitleFr(safe(aiProductOutputDto.getTitle_french()));
        labelEntity.setTitleRu(safe(aiProductOutputDto.getTitle_russian()));

        // DESCRIPTION
        labelEntity.setDescriptionEn(safe(aiProductOutputDto.getDescription_english()));
        labelEntity.setDescriptionFr(safe(aiProductOutputDto.getDescription_french()));
        labelEntity.setDescriptionRu(safe(aiProductOutputDto.getDescription_russian()));

        return labelEntity;
    }
}
