package mapper;

import domain.entity.FootballMatch;
import domain.vo.CreateFootballMatchCommand;
import dto.CreateFootballMatchCommandDTO;
import dto.FootballMatchDTO;
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
