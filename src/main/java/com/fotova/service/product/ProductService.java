package com.fotova.service.product;


import com.drools.dto.product.ProductDtoDrl;
import com.fotova.dto.ProductDtoAmq;
import com.fotova.dto.file.FileResponseDto;
import com.fotova.dto.image.ImageDto;
import com.fotova.dto.product.ProductDtoBack;
import com.drools.service.BusinessProductDroolsService;
import com.fotova.entity.ProductEntity;
import com.fotova.repository.product.ProductRepositoryImpl;
import com.fotova.service.RabbitMQProducer;
import com.fotova.service.file.FileService;
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

    @Autowired
    private FileService fileService;

    public ProductDtoBack saveProduct(ProductDtoBack product, final int categoryId) {

        ProductEntity productEntity = productMapper.mapToProductEntity(product);
        ProductEntity productEntityToMap = productRepository.saveWithCategory(productEntity,categoryId);
        return productMapper.mapToProductDtoBack(productEntityToMap);
    }

    public List<ProductDtoBack> getAllProducts() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        List<FileResponseDto> filesContent = fileService.getAllFilesContent();
        List<ProductDtoBack> productDtoBackList = productMapper.mapToProductDtoBackList(productEntityList);
        return productMapper.setFileUrlToProductDtoBackList(filesContent,productDtoBackList);
    }

    public ProductDtoBack getProductById(int productId) {
        ProductEntity productEntity = productRepository.findById(productId);
        List<FileResponseDto> filesContent = fileService.getAllFilesContent();
        List<ImageDto> imageDtoList = productRepository.getProductImages(productId);
        ProductDtoBack productDtoBack = productMapper.mapToProductDtoBack(productEntity);
        return productMapper.setFileUrlToProductDto(imageDtoList, productDtoBack, filesContent);
    }

    public String deleteProductById(int productId) {
        productRepository.deleteById(productId);
        return "Product deleted with id: " + productId;
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
