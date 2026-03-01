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

    public LabelEntity mapToLabelEntity( AiProductOutputDto aiProductOutputDto) {
        LabelEntity labelEntity = new LabelEntity();

        // TITLE
        labelEntity.setTitleEn(aiProductOutputDto.getTitle_english());
        labelEntity.setTitleFr(aiProductOutputDto.getTitle_french());
        labelEntity.setTitleRu(aiProductOutputDto.getTitle_russian());

        // DESCRIPTION
        labelEntity.setDescriptionEn(aiProductOutputDto.getDescription_english());
        labelEntity.setDescriptionFr(aiProductOutputDto.getDescription_french());
        labelEntity.setDescriptionRu(aiProductOutputDto.getDescription_russian());

        return labelEntity;
    }
}
