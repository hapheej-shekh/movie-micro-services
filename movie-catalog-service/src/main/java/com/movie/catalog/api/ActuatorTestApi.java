package com.movie.catalog.api;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class ActuatorTestApi implements InfoContributor {
	
	@Override
	public void contribute(Builder builder) {

		builder.withDetail("test", "Actuator working");
	}
}
