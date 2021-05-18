package com.example.demo.Util;

import org.hibernate.annotations.Synchronize;

import java.util.Random;
import java.util.UUID;

public class GenerateID {

    public synchronized  void generateUserID() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 57; // letter '9'
        int targetStringLength = 13;


        String generatedString = new Random().ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
    }

}
