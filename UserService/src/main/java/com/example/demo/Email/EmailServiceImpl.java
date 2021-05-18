package com.example.demo.Email;

import com.example.demo.Model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl {

    @Autowired
    public EmailFeignCall emailFeignCall;

    @Async
    public void SendEmail(Email email) {
        Boolean bol = emailFeignCall.SendEmail(email);
        System.out.println(bol);
    }



}
