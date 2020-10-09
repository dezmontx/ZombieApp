package com.zombie.app.controller;

import com.zombie.app.entity.dto.NewsDTO;
import com.zombie.app.entity.dto.NewsRequestDTO;
import com.zombie.app.entity.dto.SubscribeDTO;
import com.zombie.app.entity.dto.TweetDTO;
import com.zombie.app.service.ZombieService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/")
public class RestZombieController {

    private final ZombieService zombieService;

    public RestZombieController(ZombieService zombieService) {
        this.zombieService = zombieService;
    }

    @PostMapping("send")
    public void sendTweet(@RequestHeader String token, @RequestBody TweetDTO tweetDTO){
        zombieService.sendZombieTweet(token, tweetDTO);
    }

    @PostMapping("subscribe")
    public void subscribeOnZombie(@RequestHeader String token, @RequestBody SubscribeDTO subscribeDTO){
        zombieService.subscribeOnZombie(token, subscribeDTO.getSource());
    }

    @GetMapping("news")
    public List<NewsDTO> getNews(@RequestHeader String token, @RequestBody NewsRequestDTO newsRequestDTO){
       return zombieService.getNews(token, newsRequestDTO);
    }

    @GetMapping("graveyard")
    public List<NewsDTO> getGraveyard(@RequestHeader String token, @RequestBody NewsRequestDTO newsRequestDTO){
        return zombieService.getGraveyard(token, newsRequestDTO);
    }
}
