package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
		return "Hello from TAP!";
	}

	@Configuration
	@EnableContextInstanceData
	public static class ApplicationConfiguration {
		@Value("${hostname:N/A}")
		private String hostname;

		public String hostname(){
			return hostname;
		}
	}

}
