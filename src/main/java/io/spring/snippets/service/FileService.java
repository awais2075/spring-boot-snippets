package io.spring.snippets.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class FileService {

    @Value("${aws.s3.config.bucket-name}")
    private String bucketName;
    @Autowired
    private AmazonS3 amazonS3;

    public Map<String, String> upload(io.spring.snippets.File file) throws IOException {
        amazonS3.putObject(bucketName, file.getFileName(), getFileFromMultipartFile(file.getFile()));
        return Map.of(
                "status", "200",
                "message", "file uploaded successfully");
    }

    public byte[] download(String fileName) throws IOException {
        var content = amazonS3.getObject(bucketName, fileName)
                .getObjectContent();
        return IOUtils.toByteArray(content);
    }

    private File getFileFromMultipartFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        return convertedFile;
    }
}
