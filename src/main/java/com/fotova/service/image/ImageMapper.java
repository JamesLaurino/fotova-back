package com.fotova.service.image;

import com.fotova.dto.image.ImageDto;
import com.fotova.dto.product.CategoryInnerProductDto;
import com.fotova.dto.product.ProductDtoBack;
import com.fotova.entity.ImageEntity;
import com.fotova.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageMapper {
    public ImageDto mapToImageDto(ImageEntity imageEntity) {

        ImageDto imageDto = new ImageDto();
        imageDto.setId(imageEntity.getId());
        imageDto.setPath(imageEntity.getPath());

        if(imageEntity.getProduct() != null) {

            ProductDtoBack productDtoBack = new ProductDtoBack();
            productDtoBack.setUrl(imageEntity.getProduct().getUrl());
            productDtoBack.setId(imageEntity.getProduct().getId());
            productDtoBack.setName(imageEntity.getProduct().getName());
            productDtoBack.setPrice(imageEntity.getProduct().getPrice());
            productDtoBack.setQuantity(imageEntity.getProduct().getQuantity());
            imageDto.setProductDtoBack(productDtoBack);

            if(imageEntity.getProduct().getCategory() != null) {
                CategoryInnerProductDto categoryInnerProductDto = new CategoryInnerProductDto();
                categoryInnerProductDto.setId(imageEntity.getProduct().getCategory().getId());
                categoryInnerProductDto.setName(imageEntity.getProduct().getCategory().getName());
                productDtoBack.setCategoryInnerProductDto(categoryInnerProductDto);
            }

        }

        return imageDto;
    }

    public List<ImageDto> mapToImageDtoList(List<ImageEntity> imageEntityList) {

        List<ImageDto> imageDtoList = new ArrayList<>();

        for(ImageEntity imageEntity : imageEntityList) {
            imageDtoList.add(mapToImageDto(imageEntity));
        }

        return imageDtoList;
    }

    public ImageEntity mapToImageEntity(ImageDto imageDto) {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setId(imageDto.getId());
        imageEntity.setPath(imageDto.getPath());

        if(imageDto.getProductDtoBack() != null) {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setId(imageDto.getProductDtoBack().getId());
            imageEntity.setProduct(productEntity);
        }

        return imageEntity;
    }
}
