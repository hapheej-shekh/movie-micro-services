package com.movie.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient		// optional from Spring Cloud 2020 onwards
@EnableCircuitBreaker	// Enables Hystrix Circuit Breaker support
@EnableHystrix	// Enables circuit breaker and @HystrixCommand support
@EnableHystrixDashboard	// http://localhost:<PORT>/movie-catalog/hystrix
@EnableFeignClients		// Use Feign to communicate b/t services instead of RestTemplate, WebClient
public class MovieCatalogApp {

	/*	No need for @EnableCircuitBreaker anymore in Spring Cloud 2023+ 
	 * 	(it’s auto-integrated with Resilience4j)	*/
	
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogApp.class, args);
	}

	//This is Only Rest Template
	/*
	@Bean
	public RestTemplate userDefinedMethodName() {
		
		return new RestTemplate();
	} */
	
	@Bean
	@LoadBalanced	//This support Cloud/Eureka for Micro Service Communication
	public RestTemplate userDefinedMethodName() {
		
		/* Timeout For Slow Micro-Services	*/
		
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setConnectTimeout(1000);
		
		return new RestTemplate(factory);
	}
	
	@Bean
	@LoadBalanced
	public WebClient.Builder userDefinedBuilder(){
		return WebClient.builder();
	}
}
