package com.jonnyparko.web.webSite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
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
			helper.setTo(getEmailFromConfig());
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
	
	private String getEmailFromConfig() {
		File configFile = new File("/Users/jonathanparker/website/server/src/main/resources/application.properties");
		String username = "";
		try {
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);

			username = props.getProperty("spring.mail.username");

//			System.out.print("username is: " + username);
			reader.close();
		} catch (FileNotFoundException ex) {
			// file does not exist
		} catch (IOException ex) {
			// I/O error
		}
		return username;
	}
    
}
