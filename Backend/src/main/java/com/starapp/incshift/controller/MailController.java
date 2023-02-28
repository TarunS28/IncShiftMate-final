package com.starapp.incshift.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starapp.incshift.dto.MailDetailsDTO;

@RestController
@RequestMapping("/java")
public class MailController {

	@Autowired
	private JavaMailSender mailSender;
	@CrossOrigin("*")
	@PostMapping("/send")
	public String sendMail(@RequestBody MailDetailsDTO mailDetailsDTO) {
		try {
			
			SimpleMailMessage message = new SimpleMailMessage();
			
			message.setFrom("qt.plus.qt@gmail.com");
			message.setTo(mailDetailsDTO.getTo());
			message.setText(mailDetailsDTO.getMessage());
			message.setSubject(mailDetailsDTO.getSubject());
			
			mailSender.send(message);
		
	
			return "Success";
			
			
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
