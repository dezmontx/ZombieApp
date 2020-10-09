package com.zombie.app.service.impl;

import com.zombie.app.repository.SessionRepository;
import com.zombie.app.entity.Session;
import com.zombie.app.service.SessionService;
import com.zombie.app.utility.DateUtility;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SessionServiceImpl implements SessionService {

    private static final int SESSION_EXPIRATION_INTERVAL = 12;

    private final SessionRepository sessionRepository;

    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Boolean checkSession(String token) {
        return sessionRepository.existsSessionByTokenAndDateExpiredAfter(token, new Date());
    }

    @Override
    public void createSession(String user, String token) {
        Session session = new Session();
        session.setToken(token);
        session.setEmail(user);
        session.setDateCreation(new Date());
        session.setDateExpired(DateUtility.addHoursToDate(session.getDateCreation(), SESSION_EXPIRATION_INTERVAL));

        sessionRepository.save(session);
    }

    @Override
    public void deleteSession(String token) {
        Session session = sessionRepository.findByTokenOrderByIdDesc(token);
        if(session!=null) sessionRepository.delete(session);
    }
}
