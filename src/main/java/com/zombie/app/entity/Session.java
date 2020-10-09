package com.zombie.app.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "session")
public class Session {

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    private Long id;

    private String token;

    private String email;

    private Date dateCreation;

    private Date dateExpired;
}
