package com.bbodeum.container;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = {"com.bbodeum.member.control",
							"com.bbodeum.dog.control",
							"com.bbodeum.trainer.control",
							"com.bbodeum.course.control",
							"com.bbodeum.apply.control",
							"com.bbodeum.advice"
							})
@EnableWebMvc 
public class ServletContext 
		implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://192.168.0.176:5501")
			.allowCredentials(true)
			.allowedMethods("GET", "POST", "PUT", "DELETE");
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setPrefix("/WEB-INF/views/");
		irvr.setSuffix(".jsp");
		return irvr;
	}
	
}
