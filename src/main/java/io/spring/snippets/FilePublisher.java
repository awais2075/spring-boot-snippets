package io.spring.snippets;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FilePublisher {

    @Value("${spring.rabbitmq.queue}")
    private String queue;
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

    @GetMapping("/publish")
    public Map<String, String> publish() throws IOException {
        int minute = LocalDateTime.now().getMinute();
        String fileName = minute < 10 ? "0" + minute + fileExtension : minute + fileExtension;

        byte[] fileInBytes = Files.readAllBytes(new File(sourcePath + fileName).toPath());

        var data = new io.spring.snippets.File(fileName, destinationPath, fileInBytes);
        template.convertAndSend(exchange, routingKey, data);

        return Map.of("message", "success");
    }
}
