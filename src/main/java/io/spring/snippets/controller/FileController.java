package io.spring.snippets.controller;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import io.spring.snippets.exception.CustomException.*;
import io.spring.snippets.File;
import io.spring.snippets.exception.CustomException;
import io.spring.snippets.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public Map<String, String> upload(@RequestParam String fileName, @RequestParam MultipartFile file) throws IOException, FileWithoutExtensionException, CustomException.FileWithoutExtensionException {
        return fileService.upload(new File(fileName, file));
    }

    @GetMapping(value = "/download")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<byte[]> download(@RequestParam("fileName") String fileName) throws IOException {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileService.download(fileName));
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handleException(IOException ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(AmazonS3Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handleException(AmazonS3Exception ex) {
        return Map.of("error", ex.getMessage());
    }
}
