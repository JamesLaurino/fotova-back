package com.fotova.repository.image;

import com.fotova.entity.ImageEntity;
import com.fotova.repository.ICrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageRepositoryImpl implements ICrud<ImageEntity> {

    @Autowired
    private ImageRepositoryJpa imageRepositoryJpa;

    @Override
    public ImageEntity findById(int id) {
        return imageRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    public List<ImageEntity> findAll() {
        return imageRepositoryJpa.findAll();
    }

    @Override
    public ImageEntity save(ImageEntity imageEntity) {
        return imageRepositoryJpa.save(imageEntity);
    }

    @Override
    public void deleteAll() {
        imageRepositoryJpa.deleteAll();
    }

    @Override
    public void deleteById(int id) {
        imageRepositoryJpa.deleteById(id);
    }

    @Override
    public ImageEntity update(ImageEntity imageEntity) {
        return imageRepositoryJpa.save(imageEntity);
    }

    public void updateImagesByProductId(Integer productId) {
        imageRepositoryJpa.updateImagesByProductId(productId);
    }
}
