package io.spring.snippets.publisher;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Configuration
@EnableScheduling
public class FilePublisher {

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.routing-key}")
    private String routingKey;
    @Value("${file.src-path}")
    private String sourcePath;
    @Value("${file.dest-path}")
    private String destinationPath;
    @Value("${file.file-ext}")
    private String fileExtension;

    @Autowired
    private RabbitTemplate template;

    @Scheduled(cron = "${cron.publish-file}")
    public void publish() {
        System.err.println("next job...."+LocalDateTime.now());
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime lastDateTime = currentDateTime.minus(1, ChronoUnit.MINUTES);
        int lastMinute = currentDateTime.minus(1, ChronoUnit.MINUTES).getMinute();
        String fileName = lastMinute < 10 ? "0" + lastMinute + fileExtension : lastMinute + fileExtension;

        byte[] fileInBytes = new byte[0];
        try {
            fileInBytes = Files.readAllBytes(new File(sourcePath + fileName).toPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        var data = new io.spring.snippets.model.File(lastDateTime.toString(), fileName, destinationPath, fileInBytes);
        try {
            template.convertAndSend(exchange, routingKey, data);
        } catch (AmqpException ex) {
            ex.printStackTrace();
        }
    }
}
