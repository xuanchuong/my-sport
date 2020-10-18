package mapper;

import entity.Player;
import lombok.AllArgsConstructor;
import entity.FootballMatch;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class FootballMatchMapper {

    private final PlayerMapper playerMapper;

    public FootballMatch map(domain.entity.FootballMatch source) {
        FootballMatch target = new FootballMatch();
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setLocation(source.getLocation());
        target.setNumberOfPlayers(source.getNumberOfPlayers());

        target.setOwner(playerMapper.map(source.getOwner()));
        target.setStartDate(source.getStartDate());
        target.setTitle(source.getTitle());
        target.setMatchStatus(source.getMatchStatus());
        List<Player> participants = new ArrayList<>();
        source.getParticipants().forEach(player -> participants.add(playerMapper.map(player)));
        target.setParticipants(participants);
        return target;
    }

    public domain.entity.FootballMatch map(FootballMatch source) {
        List<domain.entity.Player> participants = new ArrayList<>();
        source.getParticipants().forEach(player -> participants.add(playerMapper.map(player)));
        return domain.entity.FootballMatch.builder()
                .id(source.getId())
                .description(source.getDescription())
                .location(source.getLocation())
                .numberOfPlayers(source.getNumberOfPlayers())
                .matchStatus(source.getMatchStatus())
                .owner(playerMapper.map(source.getOwner()))
                .startDate(source.getStartDate())
                .title(source.getTitle())

                .participants(participants).build();

    }
}
