package com.purna.mailsender.Services;

import com.purna.mailsender.Model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendRegistrationMail(Mail mail){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("Teaminnovate.api@gmail.com");
        simpleMailMessage.setTo(mail.getRecipient());
        simpleMailMessage.setSubject(mail.getSubject());
        simpleMailMessage.setText((mail.getMsgBody()));
        javaMailSender.send(simpleMailMessage);
    }
}
