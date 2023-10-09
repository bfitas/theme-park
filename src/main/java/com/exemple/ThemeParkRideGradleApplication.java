package com.exemple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ThemeParkRideGradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThemeParkRideGradleApplication.class, args);
	}
}
