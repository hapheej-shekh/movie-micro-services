package com.starter.jpa.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.mock.web.MockHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
//import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module.Feature;

//@Configuration
public class CommonConfig {

	/**	This been has been created to Convert Map<String, List<Employee>> to Json form
	 * 	Without this bean, cannot convert lazily initialized Employee.performance error coming
	 */
	/*
	@Bean
	public ObjectMapper objectMapper() {
	    ObjectMapper objectMapper = new ObjectMapper();
	    Hibernate5Module module = new Hibernate5Module();
	    module.enable(Feature.REPLACE_PERSISTENT_COLLECTIONS);
	    objectMapper.registerModule(module);
	    return objectMapper;
	}
	*/
	
}
