package com.fotova.service.file;

import com.fotova.dto.file.FileResponse;
import com.fotova.dto.file.FileResponseDto;
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

    private String cleanFileUrl(String fileUrl) {
        String cleanedFileUrl = "";
        cleanedFileUrl = fileUrl.replace("[","")
                .replace("]","")
                .replace("URL","")
                .replace(" ","");

        return cleanedFileUrl;
    }

}
