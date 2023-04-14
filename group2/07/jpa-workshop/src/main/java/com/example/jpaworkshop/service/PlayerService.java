package com.example.jpaworkshop.service;

import com.example.jpaworkshop.dto.player.CreatePlayerRequest;
import com.example.jpaworkshop.entity.Player;
import com.example.jpaworkshop.entity.Team;
import com.example.jpaworkshop.repository.PlayerRepository;
import com.example.jpaworkshop.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public Player createPlayer(CreatePlayerRequest request) {
        Player player = new Player()
            .setName(request.name())
            .setBirthDate(request.birthDate());
        if (request.teamId() != null) {
            Team team = teamRepository.findById(request.teamId())
                .orElseThrow(() -> new RuntimeException("Not found team by id = " + request.teamId()));
            player.setTeam(team);
            team.getPlayers().add(player);
        }
        return playerRepository.save(player);
    }

    @Transactional
    public Player updatePlayer(Long id, CreatePlayerRequest request) {
        Player player = playerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found player by id = " + id));
        player.setName("New interesting name");
        playerRepository.save(player);

        Team team = Optional.ofNullable(request.teamId())
            .flatMap(teamRepository::findById)
            .orElse(null);
        player.setName(request.name())
            .setBirthDate(request.birthDate())
            .setTeam(team);
        if (team != null) {
            team.getPlayers().add(player);
        }
        return playerRepository.save(player);
    }

    @Transactional(readOnly = true)
    public List<Player> getAll() {
        return playerRepository.findAll();
    }
}
