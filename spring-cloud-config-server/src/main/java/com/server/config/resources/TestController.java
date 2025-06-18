package com.server.config.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

	@Autowired
	private Environment env;
	
	@Value("${my.configuration.greeting.person:dummy}")
	private String configGreetingPerson;
	
	
	@RequestMapping("config")
	public ResponseEntity<Object> configDetail() {
		
		System.out.println("configGreetingPerson: "+configGreetingPerson);
		
		return ResponseEntity.ok(env.toString());
	}
}
