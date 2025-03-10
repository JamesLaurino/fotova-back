package com.fotova.dto.file;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class FileResponse {
    private String fileName;
    private Resource file;
}
