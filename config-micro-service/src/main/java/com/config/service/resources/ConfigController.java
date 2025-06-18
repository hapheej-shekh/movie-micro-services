package com.config.service.resources;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.service.config.DatabaseConfig;

@RestController
@RefreshScope	//All the beans/property refresh, without restart using Actuator
public class ConfigController {

	@Value("${my.configuration.start}")
	private boolean startMyConfiguration;
	
	@Value("${my.configuration.greeting}")
	private String configGreeting;
	
	@Value("${my.configuration.greeting.person}")
	private String configGreetingPerson;
	
	@Value("#{${my.configuration.db.detail}}")
	private Map<String, String> dbConfig;
	
	@Value("${undefinedProperty:undefinedProperty}")
	private String undefinedProperty;
	
	@Value("${my.configuration.list}")
	private List<Integer> myList;
	
	@Autowired
	private DatabaseConfig databaseConfig; //Using @ConfigurationPropeties(prefix)
	
	@Autowired
	private Environment env;
	
	@RequestMapping(value="/config")
	public String startConfiguration() {
		
		if(startMyConfiguration) {
			
			  System.out.println(configGreeting); 
			  System.out.println(configGreetingPerson);
			  System.out.println(dbConfig); 
			  System.out.println(undefinedProperty);
			  System.out.println(myList); 
			  System.out.println(databaseConfig);
			 
		}
		
		return configGreeting+", "+configGreetingPerson+", "+dbConfig+", "+databaseConfig;
	}
	
	@RequestMapping(value="/env")
	public String startEnvironmentConf() {
		
		return env.toString();
	}
}
