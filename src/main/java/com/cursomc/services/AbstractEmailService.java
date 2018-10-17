package com.cursomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.cursomc.domain.Order;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendOrderConfirmationMail(Order order) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromOrder(order);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromOrder(Order order) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(order.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Confirmation Order");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(order.toString());
		return sm;
	}
	
	protected String htmlFromTemplateOrder(Order order) {
		Context context = new Context();
		context.setVariable("order", order);
		return templateEngine.process("emails/orderConfirmationHTML", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Order order) {
		MimeMessage mm;
		try {
			mm = prepareMimeMessageFromOrder(order);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmationMail(order);
		}
		
	}

	private MimeMessage prepareMimeMessageFromOrder(Order order) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(order.getClient().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Order Confirmation! Order ID = " + order.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mimeMessage.setContent(htmlFromTemplateOrder(order), "text/html"); //IMPORTANTE!!
		return mimeMessage;
	}
	
	

}
