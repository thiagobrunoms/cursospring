package com.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.cursomc.services.DatabaseTestService;
import com.cursomc.services.EmailService;
import com.cursomc.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DatabaseTestService databaseTesteService;
	
	
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		databaseTesteService.initiateTestDatabase();
		return true;	
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
	
}
