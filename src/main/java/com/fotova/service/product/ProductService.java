package com.fotova.service.product;


import com.drools.dto.product.ProductDtoDrl;
import com.drools.service.BusinessProductDroolsService;
import com.fotova.dto.ContactDtoAmq;
import com.fotova.dto.ProductDtoAmq;
import com.fotova.dto.contact.ContactDto;
import com.fotova.dto.file.FileResponseDto;
import com.fotova.dto.image.ImageDto;
import com.fotova.dto.product.ProductDtoBack;
import com.fotova.dto.product.ProductPageDto;
import com.fotova.entity.ProductEntity;
import com.fotova.repository.product.ProductRepositoryImpl;
import com.fotova.service.RabbitMQProducer;
import com.fotova.service.file.FileService;
import com.fotova.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private ImageService imageService;

    public ProductDtoBack saveProduct(ProductDtoBack product, final int categoryId) {
        ProductEntity productEntity = productMapper.mapToProductEntity(product);
        ProductEntity productEntityToMap = productRepository.saveWithCategory(productEntity,categoryId);
        return productMapper.mapToProductDtoBack(productEntityToMap);
    }

    public ProductPageDto getAllProductsPaginate(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<ProductEntity> productEntityPage = productRepository.findAllPaginate(pageable);
        return productMapper.mapToPageable(productEntityPage);
    }

    public List<ProductDtoBack> getAllProductByCategoryId(Integer categoryId) {
        List<ProductEntity> productEntityList = productRepository.findProductsByCategoryId(categoryId);
        List<FileResponseDto> filesContent = fileService.getAllFilesContent();
        List<ProductDtoBack> productDtoBackList = productMapper.mapToProductDtoBackList(productEntityList);
        return productMapper.setFileUrlToProductDtoBackList(filesContent,productDtoBackList);
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
        imageService.updateImagesByProductId(productId);
        productRepository.deleteById(productId);
        return "Product deleted with id: " + productId;
    }

    public ProductDtoBack updateProduct(ProductDtoBack productDtoBack) {
        ProductEntity productEntity = productMapper.mapToProductEntity(productDtoBack);
        productRepository.save(productEntity);
        return productDtoBack;
    }

    public String sendMailFromContact(ContactDto contactDto) {
        ContactDtoAmq contactDtoAmq = new ContactDtoAmq();
        contactDtoAmq.setEmail(contactDto.getEmail());
        contactDtoAmq.setNom(contactDto.getNom());
        contactDtoAmq.setMessage(contactDto.getMessage());
        contactDtoAmq.setSujet(contactDto.getSujet());
        rabbitMQProducer.sendMessageFromContact(contactDtoAmq);
        return "Message send successfully";
    }

    /* TESTING SERVICE FOR DROOLS */
    public void testDroolsService() {
        ProductDtoDrl productDto = new ProductDtoDrl();
        productDto.setId(1);
        productDto.setName("<UNK>");
        productDto.setPrice(1.0);
        productDto.setQuantity(3);
        productDto.setCategory(null);
        businessProductDroolsService.kieService(productDto);
    }

    public List<ProductDtoBack> setDiscountForProductDrools() {

        ProductDtoBack productDto1 = new ProductDtoBack();
        productDto1.setId(1);
        productDto1.setName("Product 1");
        productDto1.setPrice(10.0);
        productDto1.setQuantity(3);

        ProductDtoBack productDto2 = new ProductDtoBack();
        productDto2.setId(2);
        productDto2.setName("Product 2");
        productDto2.setPrice(15.0);
        productDto2.setQuantity(5);

        ProductDtoBack productDto3 = new ProductDtoBack();
        productDto3.setId(3);
        productDto3.setName("Product 3");
        productDto3.setPrice(20.0);
        productDto3.setQuantity(15);

        ProductDtoBack productDto4 = new ProductDtoBack();
        productDto4.setId(4);
        productDto4.setName("Product 4");
        productDto4.setPrice(25.0);
        productDto4.setQuantity(20);

        ProductDtoBack productDto5 = new ProductDtoBack();
        productDto5.setId(5);
        productDto5.setName("Product 5");
        productDto5.setPrice(30.0);
        productDto5.setQuantity(25);

        List<ProductDtoBack> productDtoList =
                new ArrayList<>(List.of(productDto1, productDto2, productDto3, productDto4, productDto5));

        List<ProductDtoDrl> productDtoDroolslList = new ArrayList<>();

        for(ProductDtoBack productDto : productDtoList) {
            ProductDtoDrl productDtoDrl = new ProductDtoDrl();
            productDtoDrl.setId(productDto.getId());
            productDtoDrl.setName(productDto.getName());
            productDtoDrl.setPrice(productDto.getPrice());
            productDtoDrl.setQuantity(productDto.getQuantity());
            businessProductDroolsService.kieService(productDtoDrl);
            productDtoDroolslList.add(productDtoDrl);
        }

        List<ProductDtoBack> productDtoBackListUpdated = new ArrayList<>();

        for(ProductDtoDrl productDtoDrl : productDtoDroolslList) {
            ProductDtoBack productDtoBackUpdated = new ProductDtoBack();
            productDtoBackUpdated.setId(productDtoDrl.getId());
            productDtoBackUpdated.setName(productDtoDrl.getName());
            productDtoBackUpdated.setPrice(productDtoDrl.getPrice());
            productDtoBackUpdated.setQuantity(productDtoDrl.getQuantity());
            productDtoBackListUpdated.add(productDtoBackUpdated);
        }

        return productDtoBackListUpdated;
    }

    /* TESTING SERVICE FOR AMQ */
    public void testAMQPService(ProductDtoDrl productDto) {
        ProductDtoAmq product = new ProductDtoAmq();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        rabbitMQProducer.sendMessage(product.toString());
    }

}
