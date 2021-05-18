package com.example.demo.Model;

public class Email {
    private String from;
    private String to;
    private String subject;
    private int time;
    private String body;


    public Email() {
    }

    public Email(String to, String subject, int time, String body) {
        this.to = to;
        this.subject = subject;
        this.time = time;
        this.body = body;
    }

    public Email( String from, String to, String subject, int time, String body) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.time = time;
        this.body = body;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
