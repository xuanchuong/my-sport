package mapper;

import domain.entity.FootballMatch;
import dto.FootballMatchOut;
import org.mapstruct.Mapper;

import java.util.List;

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

    FootballMatchOut[] map(List<FootballMatch> source);
}
