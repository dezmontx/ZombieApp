package com.zombie.app.service;

public interface EmailService {
    void sendMessage(String to, String subject, String text);
}
