package com.zombie.app.repository;

import com.zombie.app.entity.Graveyard;
import com.zombie.app.entity.Zombie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GraveyardRepository extends CrudRepository<Graveyard, Long> {
    Graveyard findByZombie(Zombie zombie);
    List<Graveyard> findAllByZombieIn(List<Zombie> zombies);
}
