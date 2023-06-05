package com.babyshop.babyshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SendMailService {
	@Autowired
	JavaMailSender javaMailSender;

	public void sendCode(String toEmail, String code) {

		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(toEmail);
			helper.setSubject("Mã xác thực Babyshop");
			helper.setText("<html><body><h3>Mã xác thực của bạn: </h3>" + code + "</body></html>", true);
			javaMailSender.send(message);
		} catch (Exception e) {
			System.out.println("Email is not found");
		}

	}

}  
