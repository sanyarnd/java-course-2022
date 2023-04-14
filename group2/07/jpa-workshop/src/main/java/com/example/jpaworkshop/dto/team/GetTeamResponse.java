package com.example.jpaworkshop.dto.team;

public record GetTeamResponse(
    Long id,
    String name,
    String country,
    int rating
) {

}
