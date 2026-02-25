package com.fotova.service.label;

import com.fotova.dto.label.LabelDto;
import com.fotova.entity.LabelEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LabelMapper {
    public LabelDto mapToLabelDto(LabelEntity labelEntity) {
        LabelDto labelDto = new LabelDto();

        labelDto.setId(labelEntity.getId());
        labelDto.setProductId(labelEntity.getProduct().getId());

        labelDto.setTitleEn(labelEntity.getTitleEn());
        labelDto.setTitleFr(labelEntity.getTitleFr());
        labelDto.setTitleRu(labelEntity.getTitleRu());

        labelDto.setDescriptionFr(labelEntity.getDescriptionFr());
        labelDto.setDescriptionRu(labelEntity.getDescriptionRu());
        labelDto.setDescriptionEn(labelEntity.getDescriptionEn());
        return labelDto;
    }
    public List<LabelDto> mapToLabelDtoList(List<LabelEntity> labelEntityList) {

        List<LabelDto> labelDtoList = new ArrayList<>();
        for (LabelEntity labelEntity : labelEntityList) {
            LabelDto labelDto = mapToLabelDto(labelEntity);
            labelDtoList.add(labelDto);
        }
        return labelDtoList;
    }
}
