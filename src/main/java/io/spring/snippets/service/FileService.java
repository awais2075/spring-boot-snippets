package io.spring.snippets.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@Service
public class FileService {

    @Value("${file.base-path}")
    private String basePath;

    public Map<String, String> upload(MultipartFile file) throws IOException {
        var filePath = basePath.concat("/").concat(file.getOriginalFilename());

        file.transferTo(new File(filePath));
        return Map.of(
                "message", "File Uploaded Successfully",
                "filePath", filePath
        );
    }

    public byte[] download(String fileName) throws IOException {
        String filePath=basePath.concat("/").concat(fileName);
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
}
