package my.sport.controller.mapper;

import my.sport.controller.dto.FootballMatchDTO;
import my.sport.domain.entity.FootballMatch;
import my.sport.domain.vo.CreateFootballMatchCommand;
import my.sport.rest.dto.CreateFootballMatchCommandDTO;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = SET_TO_NULL,
        nullValueCheckStrategy = ALWAYS,
        uses = {PlayerControllerMapper.class}
)
public interface MatchMapper {

    CreateFootballMatchCommand map(CreateFootballMatchCommandDTO source);

    FootballMatch map(FootballMatchDTO source);
}
