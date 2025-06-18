package com.discovery.security;


/**	Add spring-boot-starter-security library as dependency
 */
/* Add Spring Security Starter, but login required
@Configuration
public class EurekaSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.ignoringRequestMatchers("/eureka/**"))
	        .authorizeHttpRequests(authz -> authz
	            .requestMatchers("/eureka/**").permitAll()
	            .anyRequest().authenticated()
	        )
	        .httpBasic(withDefaults());
	    return http.build();
	}
}
*/