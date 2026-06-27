package com.fotova.service.file;

import com.fotova.dto.file.FileResponse;
import com.fotova.dto.file.FileResponseDto;
import com.fotova.entity.ImageEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
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

    /**
     * Transforme la description d'une Resource ("URL [file:/.../mon%20fichier.jpg]")
     * en URL exploitable ("file:/.../mon fichier.jpg").
     *
     * On retire uniquement le wrapper "URL [ ... ]" puis on décode les
     * caractères percent-encodés via URI#getPath (l'espace devient "%20" dans
     * l'URI). L'ancienne implémentation supprimait tous les espaces, ce qui
     * faisait diverger le nom disque du nom stocké en base : toute image dont
     * le nom contient un espace était alors écartée lors de la réconciliation
     * (ProductMapper#setFileUrl...). On préserve le préfixe "file:" attendu côté
     * front (url-helper) pour l'extraction du dernier segment.
     */
    private String cleanFileUrl(String fileUrl) {
        String stripped = fileUrl;
        if (stripped.startsWith("URL [") && stripped.endsWith("]")) {
            stripped = stripped.substring(5, stripped.length() - 1);
        }

        try {
            URI uri = new URI(stripped);
            if (uri.getScheme() != null && uri.getPath() != null) {
                return uri.getScheme() + ":" + uri.getPath();
            }
        } catch (URISyntaxException e) {
            // URL non parsable : on retourne la chaîne sans wrapper (best effort)
        }

        return stripped;
    }

}
