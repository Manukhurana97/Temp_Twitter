package com.example.emailqueue.Producers;



import com.example.emailqueue.Models.Email;
import com.example.emailqueue.RabbitMqConfig.Config;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/Email")
public class Publisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/Send")
    public Boolean SendEmail(@RequestBody Email email){
        try {
            email.setEmail_temp_id(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend(Config.Topicname ,Config.Routingkey ,email);
            System.out.println(true);
            return true;
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println(false);
            return false;
        }

    }
}
