package com.jonnyparko.web.webSite;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	
	@Autowired
    private JavaMailSender sender;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/send", method = RequestMethod.POST, consumes = "application/json")    	
	@ResponseBody
	public HttpStatus sendMail(@RequestBody Email emailModel) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
        	helper.setTo("p4rk3rj0n@gmail.com");
        	helper.setFrom(emailModel.getFromEmail());
            helper.setText(emailModel.getMessage());
            helper.setSubject(emailModel.getFromEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
        sender.send(message);
        return HttpStatus.ACCEPTED;
    }
    
}
