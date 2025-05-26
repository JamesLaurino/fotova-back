package com.fotova.repository.product;

import com.fotova.dto.image.ImageDto;
import com.fotova.entity.CategoryEntity;
import com.fotova.entity.ProductEntity;
import com.fotova.repository.ICrud;
import com.fotova.repository.category.CategoryRepositoryJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PersistenceContext
    private EntityManager entityManager;

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

    @jakarta.transaction.Transactional
    public List<ImageDto> getProductImages(Integer productId) {

        String sql =
                "SELECT image_entity.id, image_entity.path " +
                "FROM image_entity " +
                "INNER JOIN product_entity ON image_entity.product_id = product_entity.id " +
                "WHERE product_entity.id = ?1";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, productId);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results.stream().map(row -> {
            ImageDto imageDto = new ImageDto();

            imageDto.setId(((Number) row[0]).intValue());
            imageDto.setPath(row[1].toString());

            return imageDto;

        }).collect(Collectors.toList());
    }

}
