package com.example.springboot;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import io.awspring.cloud.autoconfigure.context.ContextCredentialsAutoConfiguration;
import io.awspring.cloud.autoconfigure.context.ContextInstanceDataAutoConfiguration;
import io.awspring.cloud.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import io.awspring.cloud.autoconfigure.context.ContextResourceLoaderAutoConfiguration;
import io.awspring.cloud.autoconfigure.context.ContextStackAutoConfiguration;

import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;

@SpringBootApplication(exclude = {ContextCredentialsAutoConfiguration.class, ContextInstanceDataAutoConfiguration.class, ContextRegionProviderAutoConfiguration.class, ContextResourceLoaderAutoConfiguration.class, ContextStackAutoConfiguration.class})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

    	@Bean
	public HttpTraceRepository htttpTraceRepository() {
		return new InMemoryHttpTraceRepository();
	}
}
