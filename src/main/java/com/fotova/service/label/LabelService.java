package com.fotova.service.label;

import com.fotova.dto.label.LabelDto;
import com.fotova.entity.LabelEntity;
import com.fotova.repository.label.LabelRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {
    @Autowired
    private LabelRepositoryImpl labelRepository;

    @Autowired
    private LabelMapper labelMapper;

    public List<LabelDto> getAllLabels() {
        List<LabelEntity> labelEntities = labelRepository.findAll();
        return labelMapper.mapToLabelDtoList(labelEntities);
    }
}
