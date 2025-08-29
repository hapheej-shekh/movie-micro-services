package com.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/** Eureka server also acts as eureka client by default, To stop this
 * 	use below properties
 * eureka.client.register-with-eureka: false
 * eureka.client.fetch-registry: false
 */

@SpringBootApplication
@EnableEurekaServer
public class EurekaDiscoveryServer {

	/*	http://localhost:<PORT>/	-> To check Eureka details */
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaDiscoveryServer.class, args);
	}

}
