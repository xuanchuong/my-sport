package my.sport.controller.mapper;

import my.sport.domain.vo.CreateFootballMatchCommand;
import my.sport.rest.dto.CreateFootballMatchCommandDTO;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = SET_TO_NULL,
        nullValueCheckStrategy = ALWAYS
)
public interface MatchMapper {

    CreateFootballMatchCommand map(CreateFootballMatchCommandDTO source);
}
