package com.zombie.app.repository;

import com.zombie.app.entity.Subscription;
import com.zombie.app.entity.Zombie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
    List<Subscription> findAllBySource(Zombie source);
    List<Subscription> findAllBySubscriber(Zombie subscriber);
}
