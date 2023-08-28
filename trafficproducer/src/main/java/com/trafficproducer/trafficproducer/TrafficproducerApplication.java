package com.trafficproducer.trafficproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.trafficproducer.trafficproducer.resource.MensagemResource;

@SpringBootApplication

public class TrafficproducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficproducerApplication.class, args);
	}

}
