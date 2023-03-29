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
			.allowedOrigins("http://192.168.0.176:5500"
					,"http://127.0.0.1:5500"
					,"https://cb5f-121-165-125-220.jp.ngrok.io" //포트포워딩
					,"https://52.78.5.241" //결제api 웹훅
					)
			.allowedMethods("GET", "POST", "PUT", "PATCH")
			.allowCredentials(true)
			.maxAge(600);
	}
}
