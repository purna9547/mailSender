package com.purna.mailsender.Services;

import com.purna.mailsender.Model.Mail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendRegistrationMail(Mail mail) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom("Teaminnovate.api@gmail.com","MailSender");
        helper.setTo(mail.getRecipient());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getMsgBody(), true); // true indicates HTML content

        javaMailSender.send(mimeMessage);
    }
}
