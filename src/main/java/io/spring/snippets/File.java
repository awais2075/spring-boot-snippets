package io.spring.snippets;

import org.springframework.web.multipart.MultipartFile;

public class File {

    private String fileName;
    private MultipartFile file;

    public File() {
    }

    public File(String fileName, MultipartFile file) {
        this.fileName = fileName;
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
