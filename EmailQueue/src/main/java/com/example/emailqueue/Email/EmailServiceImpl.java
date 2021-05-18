package com.example.emailqueue.Email;


import com.example.emailqueue.Models.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(Email email)
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("test@gmail.com");
        simpleMailMessage.setTo(email.getTo());
        simpleMailMessage.setSubject(email.getSubject());
        if (email.getTime() != 0)
            simpleMailMessage.setSentDate(new Date(System.currentTimeMillis() + email.getTime()));
        simpleMailMessage.setText(email.getBody());
        System.out.println(simpleMailMessage);

        mailSender.send(simpleMailMessage);
    }
}
