package com.fotova.service.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    @Value("${file.upload.path}")
    private String pathFile;


    public String uploadFile(MultipartFile file) throws IOException {

        Files.createDirectories(Paths.get(pathFile));
        byte[] bytes = file.getBytes();
        Path path = Paths.get(pathFile + file.getOriginalFilename());
        Files.write(path, bytes);

        return "Uploaded Successfully";
    }
}
