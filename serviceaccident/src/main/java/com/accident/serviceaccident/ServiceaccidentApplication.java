package com.accident.serviceaccident;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ServiceaccidentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceaccidentApplication.class, args);
	}

}
