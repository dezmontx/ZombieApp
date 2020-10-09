package com.zombie.app.service;

import com.zombie.app.entity.dto.AuthDTO;
import com.zombie.app.entity.dto.RegisterDTO;
import com.zombie.app.entity.dto.ResponseDTO;

public interface AuthService {
    Integer registerZombie(RegisterDTO registerDTO);
    ResponseDTO authZombie(AuthDTO authDTO);
    boolean checkZombie(String token);
    void logoutZombie(String token);
}
