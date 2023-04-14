package com.example.jpaworkshop.repository;

import com.example.jpaworkshop.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

}
