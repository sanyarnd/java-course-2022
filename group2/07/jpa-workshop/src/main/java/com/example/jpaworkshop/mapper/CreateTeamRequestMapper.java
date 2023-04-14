package com.example.jpaworkshop.mapper;

import com.example.jpaworkshop.config.MapStructConfig;
import com.example.jpaworkshop.dto.team.CreateTeamRequest;
import com.example.jpaworkshop.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class)
public interface CreateTeamRequestMapper {

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "tournaments", ignore = true)
    @Mapping(target = "players", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    Team map(CreateTeamRequest request);

}
