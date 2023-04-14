package com.example.jpaworkshop.mapper;

import com.example.jpaworkshop.config.MapStructConfig;
import com.example.jpaworkshop.dto.team.GetTeamResponse;
import com.example.jpaworkshop.entity.Team;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface GetTeamResponseMapper {

    GetTeamResponse map(Team team);

    List<GetTeamResponse> map(List<Team> teams);

}
