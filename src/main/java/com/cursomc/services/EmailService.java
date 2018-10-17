package com.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Order;

public interface EmailService {
	
	public void sendOrderConfirmationMail(Order order);
	
	public void sendEmail(SimpleMailMessage message);
	
	void sendOrderConfirmationHtmlEmail(Order obj);
	
	void sendHtmlEmail(MimeMessage msg);

}
