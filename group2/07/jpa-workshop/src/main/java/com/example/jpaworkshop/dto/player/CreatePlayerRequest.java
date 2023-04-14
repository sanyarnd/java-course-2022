package com.example.jpaworkshop.dto.player;

import jakarta.annotation.Nullable;

import java.time.Instant;

public record CreatePlayerRequest(
    String name,
    Instant birthDate,
    @Nullable Long teamId
) {

}
