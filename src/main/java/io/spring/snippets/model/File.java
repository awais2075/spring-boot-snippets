package io.spring.snippets.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("file")
public class File implements Serializable {

    @Id
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

    public File(String dateTime, String fileName, String filePath) {
        this.dateTime = dateTime;
        this.fileName = fileName;
        this.filePath = filePath;
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

