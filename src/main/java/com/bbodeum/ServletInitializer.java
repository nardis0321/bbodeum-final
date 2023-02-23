package com.bbodeum;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * SpringBootServletInitializer:
 * An opinionated WebApplicationInitializer 
 * to run a SpringApplication 
 * from a traditional WAR deployment. 
 * 
 * Binds Servlet, Filter and ServletContextInitializer beans 
 * from the application context to the server. 
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BbodeumApplication.class);
	}

}