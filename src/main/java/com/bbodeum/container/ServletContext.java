package com.bbodeum.container;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc 
public class ServletContext implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://192.168.0.176:5500")
			.allowedMethods("GET", "POST", "PUT", "PATCH")
			.allowCredentials(true)
			.maxAge(600);
	}
}
