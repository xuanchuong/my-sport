package mapper;

import domain.entity.FootballMatch;
import domain.entity.Player;
import lombok.AllArgsConstructor;
import entity.JpaFootballMatch;
import entity.JpaPlayer;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class FootballMatchMapper {

    private final PlayerMapper playerMapper;

    public JpaFootballMatch map(FootballMatch source) {
        JpaFootballMatch target = new JpaFootballMatch();
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setLocation(source.getLocation());
        target.setNumberOfPlayers(source.getNumberOfPlayers());

        target.setOwner(playerMapper.map(source.getOwner()));
        target.setStartDate(source.getStartDate());
        target.setTitle(source.getTitle());
        target.setMatchStatus(source.getMatchStatus());
        List<JpaPlayer> participants = new ArrayList<>();
        source.getParticipants().forEach(player -> participants.add(playerMapper.map(player)));
        target.setPaticipants(participants);
        return target;
    }

    public FootballMatch map(JpaFootballMatch source) {
        List<Player> participants = new ArrayList<>();
        source.getPaticipants().forEach(player -> participants.add(playerMapper.map(player)));
        return FootballMatch.builder()
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
