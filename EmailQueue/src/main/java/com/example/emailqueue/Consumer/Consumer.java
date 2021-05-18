package com.example.emailqueue.Consumer;


import com.example.emailqueue.Email.EmailServiceImpl;
import com.example.emailqueue.Models.Email;
import com.example.emailqueue.RabbitMqConfig.Config;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    EmailServiceImpl emailService;

    @RabbitListener(queues = Config.Queue_name)
    public void ConsumeMessage(Email email){
       System.out.println("Email: "+email.getTo());
        emailService.sendEmail(email);
    }
}
