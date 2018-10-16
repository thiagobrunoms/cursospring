package com.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.cursomc.services.DatabaseDevService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DatabaseDevService databaseDevService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String databaseCreationStrategy;
	
	@Bean
	public boolean initiateDevConfig() throws ParseException {
		databaseDevService.initiateTestDatabase();
		
		return true;
	}

}
