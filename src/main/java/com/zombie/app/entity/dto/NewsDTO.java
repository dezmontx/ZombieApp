package com.zombie.app.entity.dto;

import com.zombie.app.entity.ZombieTweet;
import lombok.Data;

import java.util.Date;

@Data
public class NewsDTO {
    private String zombieName;
    private String zombieTweet;
    private Date date;

    public NewsDTO(ZombieTweet zombieTweet){
        this.zombieName = zombieTweet.getGraveyard().getZombie().getName();
        this.zombieTweet = zombieTweet.getMessage();
        this.date = zombieTweet.getDatePost();
    }
}
