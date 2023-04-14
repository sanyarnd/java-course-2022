package com.example.jpaworkshop.controller;

import com.example.jpaworkshop.dto.player.CreatePlayerRequest;
import com.example.jpaworkshop.dto.player.GetPlayerResponse;
import com.example.jpaworkshop.entity.Player;
import com.example.jpaworkshop.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping
    public GetPlayerResponse create(@RequestBody CreatePlayerRequest request) {
        return convert(playerService.createPlayer(request));
    }

    @PutMapping("/{id}")
    public GetPlayerResponse update(@PathVariable("id") Long id, @RequestBody CreatePlayerRequest request) {
        return convert(playerService.updatePlayer(id, request));
    }

    @GetMapping
    public List<GetPlayerResponse> getAll() {
        return playerService.getAll().stream().map(this::convert).toList();
    }

    private GetPlayerResponse convert(Player player) {
        return GetPlayerResponse.builder()
            .id(player.getId())
            .name(player.getName())
            .birthDate(player.getBirthDate())
            .team(player.getTeam() != null ? player.getTeam().getName() : null)
            .build();
    }
}
