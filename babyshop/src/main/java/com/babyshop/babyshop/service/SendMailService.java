package com.babyshop.babyshop.service;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class SendMailService {
	@Autowired
	JavaMailSender javaMailSender;

	public boolean sendCode(String toEmail, String code) {

		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(toEmail);
			helper.setSubject("Mã xác thực Babyshop");
			helper.setText("<html><body><h3>Mã xác thực của bạn: </h3>" + code + "</body></html>", true);
			javaMailSender.send(message);
			return true;
		} catch (Exception e) {
			System.out.println("Email is not found");
			return false;
		}

	}

	public void sendNewPass(String toEmail, String password) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(toEmail);
			helper.setSubject("Request to reset password");

			// Load the contents of your HTML file as a string
			FileSystemResource file = new FileSystemResource(new File("sendnewpass.html"));
			String htmlContent = IOUtils.toString(file.getInputStream(), StandardCharsets.UTF_8);
			
			String htmlAndPass = htmlContent.replace("OTPVALUE", password);
			// Set the HTML content of the email
			helper.setText(htmlAndPass, true);

			javaMailSender.send(message);
			System.out.print("DONE");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
