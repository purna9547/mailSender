package com.purna.mailsender.Services;

import com.purna.mailsender.Model.MailSender;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendMail(MailSender mailSender) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom("Teaminnovate.api@gmail.com","MailSender");
        helper.setTo(mailSender.getRecipient());
        helper.setSubject(mailSender.getSubject());
        helper.setText(mailSender.getMessage(), true); // true indicates HTML content

        javaMailSender.send(mimeMessage);
    }
}
