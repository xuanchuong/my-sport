package mapper;

import entity.FootballMatch;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = SET_TO_NULL,
        nullValueCheckStrategy = ALWAYS,
        uses = {PlayerMapper.class}
)
public interface FootballMatchMapper {

    FootballMatch map(domain.entity.FootballMatch source);

    domain.entity.FootballMatch map(FootballMatch source);
}
