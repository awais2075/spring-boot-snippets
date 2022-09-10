package io.spring.snippets.consumer;

import io.spring.snippets.model.File;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileConsumer {

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void consume(File data) throws IOException {
        String filePath = data.getFilePath();
        String fileName = data.getFileName();
        byte[] fileInBytes = data.getFileInBytes();
        Files.write(Path.of(filePath + fileName), fileInBytes);
    }
}
