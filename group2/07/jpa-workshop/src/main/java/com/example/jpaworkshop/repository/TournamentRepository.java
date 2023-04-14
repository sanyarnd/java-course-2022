package com.example.jpaworkshop.repository;

import com.example.jpaworkshop.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

}
