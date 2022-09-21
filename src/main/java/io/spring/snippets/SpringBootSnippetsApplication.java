package io.spring.snippets;

import io.spring.snippets.model.File;
import io.spring.snippets.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
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
		return fileRepository.save(new File(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")), "temp", "/tmp/file"));
	}

	@GetMapping("/getFiles")
	public List<File> getFiles() {
		var files = new ArrayList<File>();
		fileRepository.findAll().forEach(files::add);
		return files;
	}
}
