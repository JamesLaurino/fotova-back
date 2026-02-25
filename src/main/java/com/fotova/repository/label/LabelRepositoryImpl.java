package com.fotova.repository.label;

import com.fotova.entity.LabelEntity;
import com.fotova.repository.ICrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LabelRepositoryImpl implements ICrud<LabelEntity> {

    @Autowired
    private LabelRepositoryJpa labelRepository;


    @Override
    public LabelEntity findById(int id) {
        return labelRepository.findById(id).orElse(null);
    }

    @Override
    public List<LabelEntity> findAll() {
        return labelRepository.findAll();
    }

    @Override
    public LabelEntity save(LabelEntity labelEntity) {
        return labelRepository.save(labelEntity);
    }

    @Override
    public void deleteAll() {
        labelRepository.deleteAll();
    }

    @Override
    public void deleteById(int id) {
        labelRepository.deleteById(id);
    }

    @Override
    public LabelEntity update(LabelEntity labelEntity) {
        return labelRepository.save(labelEntity);
    }
}
