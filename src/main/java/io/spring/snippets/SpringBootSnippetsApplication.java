package io.spring.snippets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@SpringBootApplication
public class SpringBootSnippetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSnippetsApplication.class, args);
	}

	@GetMapping
	public String test() {
		return "hello world...";
	}
}
