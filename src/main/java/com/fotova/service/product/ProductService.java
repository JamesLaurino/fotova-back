package com.fotova.service.product;


import com.drools.dto.product.ProductDtoDrl;
import com.fotova.dto.ProductDtoAmq;
import com.fotova.dto.product.ProductDtoBack;
import com.drools.service.BusinessProductDroolsService;
import com.fotova.entity.ProductEntity;
import com.fotova.repository.product.ProductRepositoryImpl;
import com.fotova.service.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService
{
    @Autowired
    private ProductRepositoryImpl productRepository;

    @Autowired
    private BusinessProductDroolsService businessProductDroolsService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    public ProductDtoBack saveProduct(ProductDtoBack product, final int categoryId) {

        ProductEntity productEntity = productMapper.mapToProductEntity(product);
        ProductEntity productEntityToMap = productRepository.saveWithCategory(productEntity,categoryId);
        return productMapper.mapToProductDtoBack(productEntityToMap);
    }

    public List<ProductDtoBack> getAllProducts() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        return productMapper.mapToProductDtoBackList(productEntityList);
    }

    public ProductDtoBack getProductById(int productId) {
        ProductEntity productEntity = productRepository.findById(productId);
        return productMapper.mapToProductDtoBack(productEntity);
    }

    public void deleteProductById(int productId) {
        productRepository.deleteById(productId);
    }

    public ProductDtoBack updateProduct(ProductDtoBack productDtoBack) {
        ProductEntity productEntity = productMapper.mapToProductEntity(productDtoBack);
        productRepository.save(productEntity);
        return productDtoBack;
    }

    public void testDroolsService() {
        ProductDtoDrl productDto = new ProductDtoDrl();
        productDto.setId(1);
        productDto.setName("<UNK>");
        productDto.setPrice(1.0);
        productDto.setQuantity(3);
        productDto.setCategory(null);
        businessProductDroolsService.kieService(productDto);
    }

    public void testAMQPService(ProductDtoDrl productDto) {
        ProductDtoAmq product = new ProductDtoAmq();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        rabbitMQProducer.sendMessage(product.toString());
    }

}
