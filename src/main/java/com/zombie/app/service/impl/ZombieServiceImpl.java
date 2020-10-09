package com.zombie.app.service.impl;

import com.zombie.app.entity.Graveyard;
import com.zombie.app.entity.Session;
import com.zombie.app.entity.Subscription;
import com.zombie.app.entity.Zombie;
import com.zombie.app.entity.ZombieTweet;
import com.zombie.app.entity.dto.NewsDTO;
import com.zombie.app.entity.dto.NewsRequestDTO;
import com.zombie.app.entity.dto.TweetDTO;
import com.zombie.app.repository.GraveyardRepository;
import com.zombie.app.repository.SessionRepository;
import com.zombie.app.repository.SubscriptionRepository;
import com.zombie.app.repository.ZombieRepository;
import com.zombie.app.repository.ZombieTweetRepository;
import com.zombie.app.service.EmailService;
import com.zombie.app.service.ZombieService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZombieServiceImpl implements ZombieService {

    private final SessionRepository sessionRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final ZombieTweetRepository zombieTweetRepository;
    private final ZombieRepository zombieRepository;
    private final GraveyardRepository graveyardRepository;

    private final EmailService emailService;

    public ZombieServiceImpl(SessionRepository sessionRepository,
                             SubscriptionRepository subscriptionRepository,
                             ZombieTweetRepository zombieTweetRepository,
                             ZombieRepository zombieRepository,
                             GraveyardRepository graveyardRepository,
                             EmailService emailService) {
        this.sessionRepository = sessionRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.zombieTweetRepository = zombieTweetRepository;
        this.zombieRepository = zombieRepository;
        this.graveyardRepository = graveyardRepository;
        this.emailService = emailService;
    }

    @Override
    public boolean sendZombieTweet(String token, TweetDTO tweetDTO) {
        ZombieTweet zombieTweet = zombieTweetRepository.save(new ZombieTweet(tweetDTO));
        //notify other users
        List<Subscription> subscribers = subscriptionRepository.findAllBySource(zombieTweet.getGraveyard().getZombie());

        subscribers.forEach(sub -> {
            emailService.sendMessage(sub.getSubscriber().getEmail(), "New tweets!", "Your zombie friend sent some new tweets");

            sub.setLastNotifyDate(new Date());
            subscriptionRepository.save(sub);
        });

        return true;
    }

    @Override
    public List<NewsDTO> getGraveyard(String token, NewsRequestDTO newsRequestDTO) {
        Session session = sessionRepository.findByTokenOrderByIdDesc(token);

        Zombie zombie = zombieRepository.findByEmail(session.getEmail());

        Pageable getNextPage = PageRequest.of(newsRequestDTO.getNumber(),
                                              newsRequestDTO.getSize(),
                                              Sort.by("datePost").descending());

        return zombieTweetRepository.findAllByGraveyard(graveyardRepository.findByZombie(zombie), getNextPage)
                                    .stream()
                                    .map(NewsDTO::new)
                                    .collect(Collectors.toList());
    }

    @Override
    public List<NewsDTO> getNews(String token, NewsRequestDTO newsRequestDTO) {
        Session session = sessionRepository.findByTokenOrderByIdDesc(token);
        Zombie zombie = zombieRepository.findByEmail(session.getEmail());

        Pageable getNextPage = PageRequest.of(newsRequestDTO.getNumber(),
                                              newsRequestDTO.getSize(),
                                              Sort.by("datePost").descending());

        List<Subscription> getSubs = subscriptionRepository.findAllBySubscriber(zombie);

        List<Zombie> zombies = getSubs.stream()
                                      .map(el->new Zombie(el.getSource().getEmail()))
                                      .collect(Collectors.toList());

        List<Graveyard> graveyards = graveyardRepository.findAllByZombieIn(zombies);

        return zombieTweetRepository.findAllByGraveyardInOrderByDatePostDesc(graveyards, getNextPage)
                                    .stream()
                                    .map(NewsDTO::new)
                                    .collect(Collectors.toList());
    }

    @Override
    public boolean subscribeOnZombie(String token, String source) {
        Session session = sessionRepository.findByTokenOrderByIdDesc(token);

        subscriptionRepository.save(new Subscription(new Zombie(session.getEmail()), new Zombie(source)));

        return true;
    }
}
