package com.fotova.service.product;

import com.fotova.dto.product.CategoryInnerProductDto;
import com.fotova.dto.product.ProductDtoBack;
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

        CategoryInnerProductDto innerCategory = new CategoryInnerProductDto();
        innerCategory.setId(productEntity.getCategory().getId());
        innerCategory.setName(productEntity.getCategory().getName());

        productDto.setCategoryInnerProductDto(innerCategory);

        return productDto;

    }
}
