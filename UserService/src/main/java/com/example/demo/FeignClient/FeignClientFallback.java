package com.example.demo.FeignClient;

import com.example.demo.Email.EmailFeignCall;
import com.example.demo.Model.Email;
import org.springframework.stereotype.Component;

@Component
public class FeignClientFallback implements EmailFeignCall {
    @Override
    public Boolean SendEmail(Email email) {
        return false;
    }
}
