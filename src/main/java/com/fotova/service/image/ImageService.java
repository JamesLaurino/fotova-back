package com.fotova.service.image;

import com.fotova.dto.image.ImageDto;
import com.fotova.dto.product.ProductDtoBack;
import com.fotova.entity.ImageEntity;
import com.fotova.entity.ProductEntity;
import com.fotova.repository.image.ImageRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageRepositoryImpl imageRepositoryImpl;

    @Autowired
    private ImageMapper imageMapper;

    public List<ImageDto> getAllImages() {
        return imageMapper.mapToImageDtoList(imageRepositoryImpl.findAll());
    }

    public void updateImagesByProductId(Integer productId) {
        imageRepositoryImpl.updateImagesByProductId(productId);
    }

    public ImageDto getImageById(Integer id) {
        return imageMapper.mapToImageDto(imageRepositoryImpl.findById(id));
    }

    public String deleteImageById(Integer id) {
        imageRepositoryImpl.deleteById(id);
        return "Image deleted successfully with id : " + id;
    }

    public String deleteImageByImageName(String imageName,Integer productId) {
        // TODO : IMPROUVE BY RETREIVE BY NAME AND NOT ALL
        List<ImageEntity> images = imageRepositoryImpl.findAll();
        for(ImageEntity image : images) {
            if(image.getPath().equals(imageName) && image.getProduct().getId() == productId) {
                imageRepositoryImpl.deleteById(image.getId());
                return "Image deleted successfully with name : " + imageName;
            }
        }
        return "Image not found with name : " + imageName;
    }

    public String deleteAllImages() {
        imageRepositoryImpl.deleteAll();
        return "All images has been deleted successfully";
    }

    public ImageDto saveImage(ImageDto imageDto, Integer productId) {
        ImageEntity imageEntity = imageMapper.mapToImageEntity(imageDto);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productId);
        imageEntity.setProduct(productEntity);
        return imageMapper.mapToImageDto(imageRepositoryImpl.save(imageEntity));
    }

    public ImageDto updateImage(ImageDto imageDto) {
        ImageEntity imageEntity = imageRepositoryImpl.findById(imageDto.getId());
        Integer productId = imageEntity.getProduct().getId();
        ProductDtoBack productDtoBack = new ProductDtoBack();
        productDtoBack.setId(productId);
        imageDto.setProductDtoBack(productDtoBack);
        return imageMapper.mapToImageDto(imageRepositoryImpl.update(imageMapper.mapToImageEntity(imageDto)));
    }
}
