package com.fotova.service.file;

import com.fotova.dto.file.FileResponse;
import com.fotova.dto.file.FileResponseDto;
import com.fotova.entity.ImageEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileMapper {

    List<FileResponseDto> mapToFileResponseDto(List<FileResponse> files) {
        List<FileResponseDto> fileResponseDtoList = new ArrayList<>();
        for (FileResponse file : files) {
            FileResponseDto fileResponseDto = new FileResponseDto();
            fileResponseDto.setFileName(file.getFileName());
            fileResponseDto.setFile(this.cleanFileUrl(file.getFile().toString()));
            fileResponseDtoList.add(fileResponseDto);
        }

        return fileResponseDtoList;
    }

    public List<String> mapImagesPathFromProduct(List<ImageEntity> imageEntities) {
        List<String> imagesPath = new ArrayList<>();
        for (ImageEntity imageEntity : imageEntities) {
            imagesPath.add(imageEntity.getPath());
        }
        return imagesPath;
    }

    private String cleanFileUrl(String fileUrl) {
        String cleanedFileUrl = "";
        cleanedFileUrl = fileUrl.replace("[","")
                .replace("]","")
                .replace("URL","")
                .replace(" ","");

        return cleanedFileUrl;
    }

}
