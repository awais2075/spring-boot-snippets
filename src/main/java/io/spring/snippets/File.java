package io.spring.snippets;

import java.io.Serializable;

public class File implements Serializable {

    private String fileName;
    private String filePath;
    private byte[] fileInBytes;

    public File() {
    }

    public File(String fileName, String filePath, byte[] fileInBytes) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileInBytes = fileInBytes;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public byte[] getFileInBytes() {
        return fileInBytes;
    }

    public void setFileInBytes(byte[] fileInBytes) {
        this.fileInBytes = fileInBytes;
    }
}
