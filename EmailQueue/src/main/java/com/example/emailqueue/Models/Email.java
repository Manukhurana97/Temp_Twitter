package com.example.emailqueue.Models;

public class Email {
    private String email_temp_id;
    private String from;
    private String to;
    private String subject;
    private int time;
    private String body;


    public Email() {
    }

    public Email(String email_temp_id, String from, String to, String subject, int time, String body) {
        this.email_temp_id = email_temp_id;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.time = time;
        this.body = body;
    }

    public String getEmail_temp_id() {
        return email_temp_id;
    }

    public void setEmail_temp_id(String email_temp_id) {
        this.email_temp_id = email_temp_id;
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
