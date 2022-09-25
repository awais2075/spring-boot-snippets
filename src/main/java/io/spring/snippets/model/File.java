package io.spring.snippets.model;


import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Document(timeToLive = 86400)
public class File implements Serializable {

    @Id
    @Indexed
    private String dateTime;

    @Indexed
//    @Searchable
    private String fileName;
    private String filePath;
    private byte[] fileInBytes;
    private boolean isSent;

    public File() {
    }

    public File(String dateTime, String fileName, String filePath, byte[] fileInBytes) {
        this.dateTime = dateTime;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileInBytes = fileInBytes;
    }

    public File(String dateTime, String fileName, String filePath, boolean isSent) {
        this.dateTime = dateTime;
        this.fileName = fileName;
        this.filePath = filePath;
        this.isSent = isSent;
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

    public Boolean getIsSent() {
        return isSent;
    }

    public void setIsSent(Boolean sent) {
        isSent = sent;
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

