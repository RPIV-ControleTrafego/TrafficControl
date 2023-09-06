package com.trafficconsumer.trafficconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;


@SpringBootApplication
public class TrafficconsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficconsumerApplication.class, args);
	}

}
