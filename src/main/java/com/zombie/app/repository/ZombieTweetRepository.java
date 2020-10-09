package com.zombie.app.repository;

import com.zombie.app.entity.Graveyard;
import com.zombie.app.entity.ZombieTweet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ZombieTweetRepository extends PagingAndSortingRepository<ZombieTweet, Long> {
    List<ZombieTweet> findAllByGraveyard(Graveyard graveyard, Pageable pageable);
    List<ZombieTweet> findAllByGraveyardInOrderByDatePostDesc(List<Graveyard> graveyards, Pageable pageable);
}
