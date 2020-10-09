package com.zombie.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "graveyard")
public class Graveyard {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner")
    private Zombie zombie;

    public Graveyard(Zombie zombie){
        this.zombie = zombie;
    }

    public Graveyard(Long id){
        this.id = id;
    }
}
