package io.spring.snippets.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsS3Config {

    @Value("${aws.s3.config.access-key}")
    private String accessKey;

    @Value("${aws.s3.config.secret-key}")
    private String secretKey;
    @Bean
    public AmazonS3 s3Client() {
        var credentials = new BasicAWSCredentials(accessKey, secretKey);
        var credentialsProvider = new AWSStaticCredentialsProvider(credentials);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(credentialsProvider)
                .withRegion("us-west-1")
                .build();
    }
}
