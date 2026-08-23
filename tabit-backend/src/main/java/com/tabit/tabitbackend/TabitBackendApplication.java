package com.tabit.tabitbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class TabitBackendApplication {

	@Value("${spring.data.mongodb.uri}")
	private String mongoUri;

	public static void main(String[] args) {
		SpringApplication.run(TabitBackendApplication.class, args);
	}

	@org.springframework.context.annotation.Bean
	public CommandLineRunner run() {
		return args -> {
			System.out.println("=== ACTUAL MONGO URI BEING USED: [" + mongoUri + "] ===");
		};
	}

}
