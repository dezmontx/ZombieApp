package com.zombie.app.service;

public interface SessionService {
    Boolean checkSession(String token);
    void createSession(String user, String token);
    void deleteSession(String token);
}
