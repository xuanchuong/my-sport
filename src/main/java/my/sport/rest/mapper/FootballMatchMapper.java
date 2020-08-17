package my.sport.rest.mapper;

import my.sport.domain.entity.FootballMatch;
import my.sport.rest.dto.FootballMatchDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = SET_TO_NULL,
        nullValueCheckStrategy = ALWAYS,
        imports = {}
)
public class FootballMatchMapper {

    public FootballMatchDto map(FootballMatch source) {
        FootballMatchDto target = new FootballMatchDto();
        target.setId(source.getId());
        target.setOwnerId(source.getOwner().getId());
        target.setStartDate(source.getStartDate());
        target.setLocation(source.getLocation());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setNumberOfPlayers(source.getNumberOfPlayers());
        target.setParticipantIds(new ArrayList<>());
        source.getParticipants().forEach(participant -> {
            target.getParticipantIds().add(participant.getId());
        });

        return target;
    }
}
