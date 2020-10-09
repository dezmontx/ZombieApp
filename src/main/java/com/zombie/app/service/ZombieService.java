package com.zombie.app.service;

import com.zombie.app.entity.dto.NewsDTO;
import com.zombie.app.entity.dto.NewsRequestDTO;
import com.zombie.app.entity.dto.TweetDTO;

import java.util.List;

public interface ZombieService {

    boolean sendZombieTweet(String token, TweetDTO tweetDTO);

    List<NewsDTO> getGraveyard(String token, NewsRequestDTO newsRequestDTO);

    List<NewsDTO> getNews(String token, NewsRequestDTO newsRequestDTO);

    boolean subscribeOnZombie(String token, String source);
}
