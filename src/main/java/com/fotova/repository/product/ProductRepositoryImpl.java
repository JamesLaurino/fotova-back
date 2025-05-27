package com.fotova.repository.product;

import com.fotova.dto.image.ImageDto;
import com.fotova.entity.CategoryEntity;
import com.fotova.entity.ProductEntity;
import com.fotova.repository.ICrud;
import com.fotova.repository.category.CategoryRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ICrud<ProductEntity> {

    @Autowired
    private ProductRepositoryJpa productRepositoryJpa;

    @Autowired
    private CategoryRepositoryJpa categoryRepositoryJpa;


    @Override
    @Transactional
    public ProductEntity findById(int id) {
        return productRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public List<ProductEntity> findAll() {
        return productRepositoryJpa.findAll();
    }

    @Override
    @Transactional
    public ProductEntity save(ProductEntity productEntity) {;
        return productRepositoryJpa.save(productEntity);
    }

    @Override
    @Transactional
    public void deleteAll() {}

    @Override
    @Transactional
    public ProductEntity update(ProductEntity productEntity) {
        return productRepositoryJpa.save(productEntity);
    }

    @Transactional
    public ProductEntity saveWithCategory(ProductEntity productEntity, int categoryId) {
        CategoryEntity categoryEntity = categoryRepositoryJpa.findById(categoryId).get();
        productEntity.setCategory(categoryEntity);
        return productRepositoryJpa.save(productEntity);
    }

    @Override
    public void deleteById(int id) {
        productRepositoryJpa.deleteById(id);
    }


    public Page<ProductEntity> findAllPaginate(Pageable pageable) {
        return productRepositoryJpa.findAll(pageable);
    }

    public List<ImageDto> getProductImages(Integer productId) {
        List<Object[]> rawResults = productRepositoryJpa.getProductImages(productId);
        return rawResults.stream().map(row -> {
            ImageDto dto = new ImageDto();
            dto.setId(((Number) row[0]).intValue());
            dto.setPath((String) row[1]);
            return dto;
        }).collect(Collectors.toList());
    }
}
