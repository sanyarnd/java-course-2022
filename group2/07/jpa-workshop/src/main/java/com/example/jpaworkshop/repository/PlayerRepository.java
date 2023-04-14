package com.example.jpaworkshop.repository;

import com.example.jpaworkshop.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
