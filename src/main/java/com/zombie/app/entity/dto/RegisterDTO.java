package com.zombie.app.entity.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String email;
    private String name;
    private String password;
}
