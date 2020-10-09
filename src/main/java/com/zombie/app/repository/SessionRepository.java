package com.zombie.app.repository;

import com.zombie.app.entity.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface SessionRepository extends CrudRepository<Session, Long> {
    Boolean existsSessionByTokenAndDateExpiredAfter(String session, Date now);
    Session findByTokenOrderByIdDesc(String token);
}
