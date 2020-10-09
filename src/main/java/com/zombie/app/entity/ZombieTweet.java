package com.zombie.app.entity;

import com.zombie.app.entity.dto.TweetDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "zombie_tweet")
@NoArgsConstructor
public class ZombieTweet{

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private Long id;
    private String message;

    private Date datePost = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "graveyard")
    private Graveyard graveyard;

    public ZombieTweet(TweetDTO tweetDTO){
        this.message = tweetDTO.getMessage();
        this.graveyard = new Graveyard(tweetDTO.getGraveyardId());
    }
}
