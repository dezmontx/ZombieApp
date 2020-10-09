package com.zombie.app.repository;

import com.zombie.app.entity.Zombie;
import org.springframework.data.repository.CrudRepository;

public interface ZombieRepository extends CrudRepository<Zombie,String> {
    Zombie findByEmail(String email);
}
