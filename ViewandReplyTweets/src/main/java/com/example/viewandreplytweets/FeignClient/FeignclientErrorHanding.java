package com.example.viewandreplytweets.FeignClient;

import feign.Response;
import feign.codec.ErrorDecoder;
import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;

public class FeignclientErrorHanding implements ErrorDecoder {

    @Override
    public Exception decode(String key, Response response){
        switch (response.status()){
            case 400:
                return new BadHttpRequest();
            case 404:
                return new NotFoundException("Can't connect to email server");
            default:
                return new Exception("Error");
        }
    }
}
