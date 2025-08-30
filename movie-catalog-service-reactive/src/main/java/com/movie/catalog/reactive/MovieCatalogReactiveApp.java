package com.movie.catalog.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient		// optional from Spring Cloud 2020 onwards
@EnableFeignClients		// Use Feign to communicate b/t services instead of RestTemplate, WebClient
public class MovieCatalogReactiveApp {

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogReactiveApp.class, args);
	}
}
