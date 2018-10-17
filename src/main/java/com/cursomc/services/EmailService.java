package com.cursomc.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Order;

@Service
public interface EmailService {
	
	public void sendOrderConfirmationMail(Order order);
	
	public void sendEmail(SimpleMailMessage message);

}
