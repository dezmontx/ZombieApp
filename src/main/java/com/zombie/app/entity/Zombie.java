package com.zombie.app.entity;

import com.zombie.app.entity.dto.RegisterDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "zombie")
@NoArgsConstructor
public class Zombie {

    @Id
    private String email;
    private String name;
    private String password;

    public Zombie(RegisterDTO registerDTO){
        this.email = registerDTO.getEmail();
        this.name = registerDTO.getName();
        this.password = registerDTO.getPassword();
    }

    public Zombie(String email){
        this.email = email;
    }
}
