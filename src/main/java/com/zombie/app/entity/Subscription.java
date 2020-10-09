package com.zombie.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "subscription")
@NoArgsConstructor
public class Subscription {

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private Zombie subscriber;

    @ManyToOne
    @JoinColumn(name = "source_id")
    private Zombie source;

    private Date lastNotifyDate = new Date();

    public Subscription(Zombie subscriber, Zombie source){
        this.subscriber = subscriber;
        this.source = source;
    }
}
