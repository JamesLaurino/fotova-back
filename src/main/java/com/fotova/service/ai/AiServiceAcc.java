package com.fotova.service.ai;

import com.fotova.entity.LabelEntity;
import com.fotova.entity.ProductEntity;
import com.fotova.repository.label.LabelRepositoryImpl;
import com.fotova.repository.product.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("acc")
public class AiServiceAcc implements AiService {

    @Autowired
    private ProductRepositoryImpl productRepository;

    @Autowired
    private LabelRepositoryImpl labelRepository;

    public String translateTitleAndDescription(ProductEntity product) {

        ProductEntity productEntity = productRepository.save(product);

        LabelEntity labelEntity = new LabelEntity();
        labelEntity.setDescriptionEn("N/A");
        labelEntity.setDescriptionRu("N/A");
        labelEntity.setDescriptionFr("N/A");
        labelEntity.setTitleEn("N/A");
        labelEntity.setTitleRu("N/A");
        labelEntity.setTitleEn("N/A");
        labelEntity.setProduct(productEntity);
        labelRepository.save(labelEntity);
        return "Labels translated and added successfully";
    }
}
