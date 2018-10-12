package com.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.cursomc.domain.CreditCardPayment;
import com.cursomc.domain.TicketPayment;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfig {
	
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder buider = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(TicketPayment.class);
				objectMapper.registerSubtypes(CreditCardPayment.class);
				super.configure(objectMapper);
			}
		};
		
		return buider;
	}

}
