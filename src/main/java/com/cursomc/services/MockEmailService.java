package com.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage message) {
		LOG.info("Simulating emails send...");
		LOG.info(message.toString());
		LOG.info("Message sent!!");
	}

	@Override
	public void sendHtmlEmail(MimeMessage message) {
		LOG.info("Simulating mime emails send...");
		LOG.info(message.toString());
		LOG.info("Message sent!!");
		
	}

}
