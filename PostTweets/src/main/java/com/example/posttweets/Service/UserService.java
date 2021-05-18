package com.example.posttweets.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface UserService {

    public Map.Entry<String, String> usercall(String token) throws ExecutionException, InterruptedException;
}
