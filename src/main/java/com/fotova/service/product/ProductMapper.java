package com.fotova.service.product;

import com.fotova.dto.file.FileResponseDto;
import com.fotova.dto.image.ImageDto;
import com.fotova.dto.product.CategoryInnerProductDto;
import com.fotova.dto.product.ProductDtoBack;
import com.fotova.entity.CategoryEntity;
import com.fotova.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductMapper {

    public List<ProductDtoBack> mapToProductDtoBackList(List<ProductEntity> products){
        List<ProductDtoBack> productDtoList = new ArrayList<>();

        for(ProductEntity productEntity : products) {
            ProductDtoBack productDto = mapToProductDto(productEntity);
            productDtoList.add(productDto);
        }

        return productDtoList;
    }

    public ProductDtoBack setFileUrlToProductDto(List<ImageDto> fileDto, ProductDtoBack productDto,List<FileResponseDto> filesContent){

        List<String> imageList = new ArrayList<>();

        for(ImageDto imageDto : fileDto) {
            String image = imageDto.getPath();
            imageList.add(image);
        }

        productDto.setImages(imageList);
        return setFileUrlToProductDtoFromImageDto(filesContent,productDto);
    }

    private ProductDtoBack setFileUrlToProductDtoFromImageDto(List<FileResponseDto> filesContent,ProductDtoBack productDto)
    {
        List<String> images = productDto.getImages();
        List<String> newImages = new ArrayList<>();

        for(FileResponseDto fileResponseDto : filesContent)
        {
            images.forEach((image) -> {
                if(fileResponseDto.getFile().contains(image)) {
                    String imagesURL = fileResponseDto.getFile();
                    newImages.add(imagesURL);
                }
            });
            productDto.setImages(newImages);
        }
        return productDto;
    }

    public List<ProductDtoBack> setFileUrlToProductDtoBackList(List<FileResponseDto> filesContent,List<ProductDtoBack> productDtoBackList){
        for(FileResponseDto fileResponseDto : filesContent) {
            productDtoBackList.forEach((productDto) -> {
               if(fileResponseDto.getFile().contains(productDto.getUrl())) {
                   productDto.setUrl(fileResponseDto.getFile());
               }
            });
        }
        return productDtoBackList;
    }

    public ProductDtoBack mapToProductDtoBack(ProductEntity productEntity){
        return mapToProductDto(productEntity);
    }

    private ProductDtoBack mapToProductDto(ProductEntity productEntity){
        ProductDtoBack productDto = new ProductDtoBack();
        productDto.setId(productEntity.getId());
        productDto.setName(productEntity.getName());
        productDto.setPrice(productEntity.getPrice());
        productDto.setQuantity(productEntity.getQuantity());
        productDto.setUrl(productEntity.getUrl());

        if(productEntity.getCategory()!=null){
            CategoryInnerProductDto innerCategory = new CategoryInnerProductDto();
            innerCategory.setId(productEntity.getCategory().getId());
            innerCategory.setName(productEntity.getCategory().getName());

            productDto.setCategoryInnerProductDto(innerCategory);
        }
        else {
            productDto.setCategoryInnerProductDto(null);
        }

        return productDto;
    }

    public ProductEntity mapToProductEntity(ProductDtoBack productDtoBack){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDtoBack.getId());
        productEntity.setName(productDtoBack.getName());
        productEntity.setPrice(productDtoBack.getPrice());
        productEntity.setQuantity(productDtoBack.getQuantity());
        productEntity.setUrl(productDtoBack.getUrl());

        if(productDtoBack.getCategoryInnerProductDto() != null){
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(productDtoBack.getCategoryInnerProductDto().getId());
            categoryEntity.setName(productDtoBack.getCategoryInnerProductDto().getName());
            productEntity.setCategory(categoryEntity);
        }

        return productEntity;
    }

}
