package com.example.jpaworkshop.mapper;

import com.example.jpaworkshop.config.MapStructConfig;
import com.example.jpaworkshop.dto.team.GetEnrichedTeamResponse;
import com.example.jpaworkshop.entity.Player;
import com.example.jpaworkshop.entity.Team;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface GetEnrichedTeamResponseMapper {

    List<GetEnrichedTeamResponse> map(List<Team> teams);

    GetEnrichedTeamResponse map(Team team);

    default String map(Player player) {
        return player.getName();
    }

}
