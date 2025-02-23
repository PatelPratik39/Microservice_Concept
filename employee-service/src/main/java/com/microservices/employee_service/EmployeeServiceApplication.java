package com.microservices.employee_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class EmployeeServiceApplication {

//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}

//	Configure WebClinet from webflux dependency
	public WebClient webClient(){
		return WebClient.builder().build();
	};

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
		System.err.println("Employee Service Application Started Successfully ✅✅✅✅");
	}

}