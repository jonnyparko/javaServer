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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	
    @Autowired
    private JavaMailSender sender;

    	@CrossOrigin(origins = "http://localhost:4200")
    	@GetMapping("/send")    
    	@ResponseBody
    	public ResponseEntity sendMail() {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo("demo@gmail.com");
            helper.setText("Greetings :)");
            helper.setSubject("Mail From Spring Boot");
        } catch (MessagingException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
        }
        sender.send(message);
        return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
    }
    
}
