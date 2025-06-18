package com.starter.jpa.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaRepositories(basePackages = {"com.jpa.service.repositories"})
//@EntityScan(basePackages = {"com.jpa.service.beans"})
@SpringBootApplication
public class SpringJpaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaServiceApplication.class, args);
	}
}
