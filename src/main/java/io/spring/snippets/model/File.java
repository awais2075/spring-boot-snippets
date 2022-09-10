package io.spring.snippets.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

public class File implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private String dateTime;
    private String fileName;
    private String filePath;
    private byte[] fileInBytes;

    public File() {
    }

    public File(String dateTime, String fileName, String filePath, byte[] fileInBytes) {
        this.dateTime = dateTime;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileInBytes = fileInBytes;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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

    @Override
    public String toString() {
        return "File{" +
                "dateTime=" + dateTime +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
