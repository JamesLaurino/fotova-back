package com.fotova.service.product;

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

            ProductDtoBack productDto = new ProductDtoBack();
            productDto.setId(productEntity.getId());
            productDto.setName(productEntity.getName());
            productDto.setPrice(productEntity.getPrice());
            productDto.setQuantity(productEntity.getQuantity());
            productDto.setUrl(productEntity.getUrl());

            CategoryInnerProductDto innerCategory = new CategoryInnerProductDto();
            innerCategory.setId(productEntity.getCategory().getId());
            innerCategory.setName(productEntity.getCategory().getName());

            productDto.setCategoryInnerProductDto(innerCategory);
            productDtoList.add(productDto);

        }

        return productDtoList;
    }

    public ProductDtoBack mapToProductDtoBack(ProductEntity productEntity){

        ProductDtoBack productDto = new ProductDtoBack();
        productDto.setId(productEntity.getId());
        productDto.setName(productEntity.getName());
        productDto.setPrice(productEntity.getPrice());
        productDto.setQuantity(productEntity.getQuantity());
        productDto.setUrl(productEntity.getUrl());

        CategoryInnerProductDto innerCategory = new CategoryInnerProductDto();
        innerCategory.setId(productEntity.getCategory().getId());
        innerCategory.setName(productEntity.getCategory().getName());

        productDto.setCategoryInnerProductDto(innerCategory);

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
