package com.fotova.service.file;

import com.fotova.dto.file.FileDto;
import com.fotova.dto.file.FileResponse;
import com.fotova.dto.file.FileResponseDto;
import com.fotova.entity.ImageEntity;
import com.fotova.exception.DataExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileService {

    @Value("${file.serve.path}")
    private String pathFile;

    @Autowired
    private FileMapper fileMapper;

    public void deleteSecondaryImagesFromServerByName(List<ImageEntity> imageEntities) {
        List<String> imagesPath = fileMapper.mapImagesPathFromProduct(imageEntities);
        for (String fileName : imagesPath) {
           deleteImage(fileName);
        }
    }

    public void deleteImage(String fileName) {
        Path folder = Path.of(pathFile);
        try {
            Path filePath = folder.resolve(fileName);

            if (Files.deleteIfExists(filePath)) {
                System.out.println("Supprimé : " + filePath);
            } else {
                System.out.println("Non trouvé : " + filePath);
            }

        } catch (IOException e) {
            throw new DataExistException("Image non trouvée en erreur : " + fileName);
        }
    }

    public String uploadFile(MultipartFile file) throws IOException {

        Files.createDirectories(Paths.get(pathFile));
        byte[] bytes = file.getBytes();
        Path path = Paths.get(pathFile + file.getOriginalFilename());
        Files.write(path, bytes);

        return "Uploaded Successfully";
    }

    public FileDto getAllFiles() {

        File directoryPath = new File(pathFile);

        FileFilter fileFilter = new FileFilter(){
            public boolean accept(File dir) {
                if (dir.isFile()) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        File[] list = directoryPath.listFiles(fileFilter);
        List<String> filesList = new ArrayList<>();
        for(File fileName : list) {
            filesList.add(fileName.getName());
        }

        FileDto fileDto = new FileDto();
        fileDto.setFilelist(filesList);

        return fileDto;
    }

    public ResponseEntity<Resource> getFileByName(String filename) throws IOException {

        Path path = Paths.get(pathFile + filename);

        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new UrlResource(path.toUri());
        String contentType = Files.probeContentType(path);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType != null ? contentType : "application/octet-stream"))
                .body(resource);
    }

    public List<FileResponseDto> getAllFilesContent() {
        List<FileResponse> filesList = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(pathFile), 1)) { // 1 = Ne pas parcourir les sous-dossiers
            filesList = paths
                    .filter(Files::isRegularFile) // Ne prendre que les fichiers
                    .map(path -> {
                        try {
                            Resource resource = new UrlResource(path.toUri());
                            return new FileResponse(path.getFileName().toString(), resource);
                        } catch (IOException e) {
                            return null;
                        }
                    })
                    .filter(file -> file != null)
                    .collect(Collectors.toList());

            return  fileMapper.mapToFileResponseDto(filesList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
