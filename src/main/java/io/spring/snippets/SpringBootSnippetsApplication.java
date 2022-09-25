package io.spring.snippets;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import io.spring.snippets.model.File;
import io.spring.snippets.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
@EnableRedisDocumentRepositories(basePackages = "io.spring.snippets.*")
@Configuration
@SpringBootApplication
public class SpringBootSnippetsApplication {

	@Autowired
	private FileRepository fileRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSnippetsApplication.class, args);
	}

	@GetMapping
	public String test() {
		return "hello world...";
	}

	@GetMapping("/addFile")
	public File addFile() {
		var dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")).toString();
		return fileRepository.save(new File(dateTime, "file", "/tmp/file", false));
	}

	@GetMapping("/getFiles")
	public List<File> getFiles() {
		var files = new ArrayList<File>();
		fileRepository.findAll().forEach(files::add);
		return files;
	}

	@GetMapping("/find/{fileName}")
	public File findByDateTime(@PathVariable("fileName") String fileName) {
		return fileRepository.findByFileName(fileName)
				.orElse(new File());
	}
}
