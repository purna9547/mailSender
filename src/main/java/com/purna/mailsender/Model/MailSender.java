package com.purna.mailsender.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MailSender {
    private String recipient;
    private String subject;
    private String message;
}
