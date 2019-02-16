package com.jonnyparko.web.webSite;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	
    private String response = null;
	@Autowired
    private JavaMailSender sender;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/send", method = RequestMethod.POST, consumes = "application/json")    	
	@ResponseBody
	public String sendMail(@RequestBody Email emailModel) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo("demo@gmail.com");
            helper.setText(emailModel.getMessage());
            helper.setSubject("Mail From Spring Boot");
        } catch (MessagingException e) {
            e.printStackTrace();
            return response = "Fail";
        }
        sender.send(message);
        return response = "Success";
    }
    
}
