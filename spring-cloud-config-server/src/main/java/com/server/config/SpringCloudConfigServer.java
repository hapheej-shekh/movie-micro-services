package com.server.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfigServer {

	// http:localhost<PORT>/<property-file-name(application-dev)>/<profile(default/dev/qa)>
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServer.class, args);
	}
}
