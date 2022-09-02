package io.spring.snippets.controller;

import io.spring.snippets.CustomException.*;
import io.spring.snippets.File;
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
    public Map<String, String> upload(@RequestParam String fileName, @RequestParam MultipartFile file) throws IOException, FileWithoutExtensionException {
        return fileService.upload(new File(fileName, file));
    }

    @GetMapping(value = "/download/{fileName}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<byte[]> download(@PathVariable String fileName) throws IOException, FileWithoutExtensionException, FileNotFoundException {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileService.download(fileName));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handleException(FileNotFoundException ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handleException(FileWithoutExtensionException ex) {
        return Map.of("error", ex.getMessage());
    }
}
