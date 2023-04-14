package com.example.jpaworkshop.dto.team;

public record CreateTeamRequest(
    String name,
    String country,
    int rating
) {

}
