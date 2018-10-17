package com.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SMTPEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage message) {
		LOG.info("Sending real email...");
		LOG.info(message.toString());
		LOG.info("Message sent!!");
		mailSender.send(message);
	}

	@Override
	public void sendHtmlEmail(MimeMessage message) {
		LOG.info("Sending real HTML email...");
		LOG.info(message.toString());
		LOG.info("Message sent!!");
		javaMailSender.send(message);
	}

}
