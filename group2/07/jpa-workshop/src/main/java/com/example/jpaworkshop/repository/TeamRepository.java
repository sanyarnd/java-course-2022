package com.example.jpaworkshop.repository;

import com.example.jpaworkshop.entity.Team;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findAllByCountryIn(List<String> countries);

    @Query("select t from Team t where t.rating >= :rating")
    List<Team> findTeamsByRating(@Param("rating") int rating);

    @Query("select t from Team t left join fetch t.players")
    List<Team> findAllWithPlayers();

    @EntityGraph(attributePaths = {"players"})
    List<Team> findAll();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select t from Team t where t.id = :id")
    Optional<Team> findByIdWithPessimisticLock(@Param("id") Long id);

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query("select t from Team t where t.id = :id")
    Optional<Team> findByIdWithOptimisticLock(@Param("id") Long id);
}
