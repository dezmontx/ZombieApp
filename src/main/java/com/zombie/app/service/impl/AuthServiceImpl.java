package com.zombie.app.service.impl;

import com.zombie.app.entity.Graveyard;
import com.zombie.app.repository.GraveyardRepository;
import com.zombie.app.repository.ZombieRepository;
import com.zombie.app.entity.Zombie;
import com.zombie.app.entity.dto.AuthDTO;
import com.zombie.app.entity.dto.RegisterDTO;
import com.zombie.app.entity.dto.ResponseDTO;
import com.zombie.app.service.AuthService;
import com.zombie.app.service.SessionService;
import com.zombie.app.utility.TokenUtility;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final static int SUCCESS_CODE = 1;
    private final static int FAIL_CODE = 0;
    private final static int ALREADY_EXISTS_CODE = -1;

    private final ZombieRepository zombieRepository;
    private final GraveyardRepository graveyardRepository;

    private final SessionService sessionService;

    public AuthServiceImpl(ZombieRepository zombieRepository,
                           GraveyardRepository graveyardRepository,
                           SessionService sessionService) {
        this.zombieRepository = zombieRepository;
        this.graveyardRepository = graveyardRepository;
        this.sessionService = sessionService;
    }

    @Override
    public Integer registerZombie(RegisterDTO registerDTO) {
        if(zombieRepository.findById(registerDTO.getEmail())
                           .orElse(null)!=null){
            return ALREADY_EXISTS_CODE;
        }

        Zombie zombie = zombieRepository.save(new Zombie(registerDTO));

        graveyardRepository.save(new Graveyard(zombie));

        return SUCCESS_CODE;
    }

    @Override
    public ResponseDTO authZombie(AuthDTO authDTO) {
        Zombie zombie;
        try {
            zombie = zombieRepository.findById(authDTO.getEmail())
                                                 .orElseThrow(RuntimeException::new);
        }catch (Exception e){
            return new ResponseDTO(-1);
        }

        if(authDTO.getPassword().equals(zombie.getPassword())){
            String token = TokenUtility.createToken(zombie.getName());

            sessionService.createSession(zombie.getEmail(), token);

            return new ResponseDTO(1, token);
        }

        return new ResponseDTO(0);
    }

    @Override
    public boolean checkZombie(String token) {
        return sessionService.checkSession(token);
    }

    @Override
    public void logoutZombie(String token) {
        sessionService.deleteSession(token);
    }
}
