package com.example.resthello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.resthello.db")
@EnableJpaRepositories(basePackages = "com.example.resthello.db")
public class RestHelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestHelloApplication.class, args);
	}

}
