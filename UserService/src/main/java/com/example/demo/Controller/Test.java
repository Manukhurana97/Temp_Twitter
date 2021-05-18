package com.example.demo.Controller;

import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public class Test {

    public static void main(String[] args) {


//        int leftLimit = 47; // numeral '0'
//        int rightLimit = 95; // letter '9'
//        int targetStringLength = 9;
//
//        for (int j=0;j<50;j++) {
//
//            String generatedString = new Random().ints(leftLimit, rightLimit + 1)
//                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 95))
//                    .limit(targetStringLength)
//                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                    .toString();
//
//
//            System.out.println(generatedString);
////            System.out.println(Base64.getEncoder().withoutPadding().encodeToString(generatedString.getBytes()));
//
//        }


//        int leftLimit = 48; // numeral '0'
//        int rightLimit = 57; // letter 'z'
//        int targetStringLength = 13;
//        for (int j=0;j<50;j++) {
//            String generatedString = new Random().ints(leftLimit, rightLimit + 1)
//                    .limit(targetStringLength)
//                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                    .toString();
//
//
//
//            System.out.println(generatedString);

        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis() + 1000 * 24 * 3600 );
        System.out.println(System.currentTimeMillis() + 1000 * 24 * 3600 * 7 );
//        }
    }
}
