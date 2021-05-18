package com.example.demo.Email;


import com.example.demo.FeignClient.FeignClientFallback;
import com.example.demo.Model.Email;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@FeignClient(name ="http://localhost:8001", fallback = FeignClientFallback.class)
public interface EmailFeignCall {

    @PostMapping("/Email/Send")
    @LoadBalanced
    public Boolean SendEmail(@RequestBody Email email);

}
