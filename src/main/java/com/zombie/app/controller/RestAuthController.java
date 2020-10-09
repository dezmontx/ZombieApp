package com.zombie.app.controller;

import com.zombie.app.entity.dto.AuthDTO;
import com.zombie.app.entity.dto.RegisterDTO;
import com.zombie.app.entity.dto.ResponseDTO;
import com.zombie.app.service.AuthService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/")
public class RestAuthController {

    private final AuthService authService;

    public RestAuthController(AuthService authService) {this.authService = authService;}

    @PostMapping("login")
    private ResponseDTO authZombie(@RequestBody AuthDTO authDTO){
        return authService.authZombie(authDTO);
    }

    @PostMapping("logout")
    private void logoutZombie(@RequestHeader String token){
        authService.logoutZombie(token);
    }

    @PostMapping("register")
    private Integer registerZombie(@RequestBody RegisterDTO registerDTO){
        return authService.registerZombie(registerDTO);
    }
}
