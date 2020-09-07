package my.sport.rest.mapper;

import my.sport.domain.entity.FootballMatch;
import my.sport.rest.dto.FootballMatchOut;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = SET_TO_NULL,
        nullValueCheckStrategy = ALWAYS,
        imports = {UserMapper.class}
)
public interface FootballMatchMapper {

    FootballMatchOut map(FootballMatch source);
}
