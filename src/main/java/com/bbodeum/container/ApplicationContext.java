package com.bbodeum.container;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class ApplicationContext {
	
	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy"); //sql구문도 추적
		config.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@Localhost:1521:xe");
		config.setUsername("bbotest");
		config.setPassword("bbotest");
		config.setMinimumIdle(1);
		return config;
	}
	
	@Bean
	public HikariDataSource dataSourceHikari() {
		return new HikariDataSource(hikariConfig());
	}
}
