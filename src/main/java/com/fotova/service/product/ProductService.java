package com.fotova.service.product;


import com.drools.dto.product.ProductDto;
import com.drools.service.BusinessProductDroolsService;
import com.fotova.entity.CommentEntity;
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
    private RabbitMQProducer rabbitMQProducer;


    //TODO : add mapping for productEntity
    public ProductEntity saveProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    //TODO : add mapping for productEntity
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductEntity getProductById(int productId) {
        return productRepository.findById(productId);
    }

    //TODO : adapt wit real drool
    public void testDroolsService() {
        ProductDto productDto = new ProductDto();
        productDto.setId(1);
        productDto.setName("<UNK>");
        productDto.setPrice(1.0);
        productDto.setQuantity(3);
        productDto.setCategory(null);
        businessProductDroolsService.kieService(productDto);
    }

    public void testAMQPService(ProductDto productDto) {
        com.fotova.dto.ProductDto product = new com.fotova.dto.ProductDto();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        rabbitMQProducer.sendMessage(product);
    }

    //    public Integer addProduct(int a, int b) {
    //        return a + b;
    //    }
    //
    //    public List<String> getAllProductsMock() {
    //
    //        List<String> products = new ArrayList<>();
    //        products.add("Product 1");
    //        products.add("Product 2");
    //
    //        return products;
    //    }
}
