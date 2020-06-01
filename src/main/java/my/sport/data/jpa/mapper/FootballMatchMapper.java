package my.sport.data.jpa.mapper;

import lombok.AllArgsConstructor;
import my.sport.data.jpa.entity.JpaFootballMatch;
import my.sport.data.jpa.entity.JpaPlayer;
import my.sport.domain.entity.FootballMatch;
import my.sport.domain.entity.Player;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class FootballMatchMapper {

    private PlayerMapper playerMapper;

    public JpaFootballMatch map(FootballMatch source) {
        JpaFootballMatch target = new JpaFootballMatch();
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setLocation(source.getLocation());
        target.setNumberOfPlayers(source.getNumberOfPlayers());

        target.setOwner(playerMapper.map(source.getOwner()));
        target.setStartDate(source.getStartDate());
        target.setTitle(source.getTitle());
        List<JpaPlayer> participants = new ArrayList<>();
        source.getPaticipants().forEach(player -> {
            participants.add(playerMapper.map(player));
        });
        target.setPaticipants(participants);
        return target;
    }

    public FootballMatch map(JpaFootballMatch source) {
        List<Player> participants = new ArrayList<>();
        source.getPaticipants().forEach(player -> {
            participants.add(playerMapper.map(player));
        });
        return FootballMatch.builder()
                .id(source.getId())
                .description(source.getDescription())
                .location(source.getLocation())
                .numberOfPlayers(source.getNumberOfPlayers())

                .owner(playerMapper.map(source.getOwner()))
                .startDate(source.getStartDate())
                .title(source.getTitle())

                .paticipants(participants).build();

    }
}
