package io.spring.snippets.service;

import io.spring.snippets.CustomException.*;
import io.spring.snippets.service.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Objects;

@Service
public class FileService {

    @Value("${file.base-path}")
    private String basePath;

    public Map<String, String> upload(io.spring.snippets.File file) throws IOException, FileWithoutExtensionException {
        var filePath = basePath.concat("/")
                .concat(file.getFileName())
                .concat(".")
                .concat(Utils.getFileExtension(file.getFile().getOriginalFilename()));

        file.getFile().transferTo(new File(filePath));
        return Map.of(
                "message", "File Uploaded Successfully",
                "filePath", filePath
        );
    }

    public byte[] download(String fileName) throws FileWithoutExtensionException, FileNotFoundException {
        if(Objects.isNull(StringUtils.getFilenameExtension(fileName))) {
            throw new FileWithoutExtensionException("Filename without extension");
        }

        String filePath=basePath.concat("/").concat(fileName);
        byte[] images;
        try {
            images = Files.readAllBytes(new File(filePath).toPath());
        } catch (IOException e) {
            throw new FileNotFoundException(fileName.concat(" not found"));
        }
        return images;
    }
}
