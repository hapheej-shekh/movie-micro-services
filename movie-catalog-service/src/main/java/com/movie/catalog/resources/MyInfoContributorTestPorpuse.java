package com.movie.catalog.resources;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class MyInfoContributorTestPorpuse implements InfoContributor {
	
	@Override
	public void contribute(Builder builder) {

		builder.withDetail("test", "Actuator working");
	}
}
