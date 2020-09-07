package my.sport.application.service;

import my.sport.domain.entity.FootballMatch;
import my.sport.domain.entity.Player;
import my.sport.domain.repository.FootballMatchRepository;
import my.sport.rest.dto.CreateFootballMatchCommandDTO;

import java.util.ArrayList;
import java.util.List;

public class FootballMatchService {

    FootballMatchRepository footballMatchRepository;

    PlayerService playerService;

    public FootballMatchService(FootballMatchRepository footballMatchRepository, PlayerService playerService) {
        this.footballMatchRepository = footballMatchRepository;
        this.playerService = playerService;
    }

    public List<FootballMatch> getAllMatch() {
        return footballMatchRepository.findAll();
    }

    public FootballMatch getMatchById(Long id) {
        return footballMatchRepository.findById(id).orElse(null);
    }

    public FootballMatch createNewMatch(CreateFootballMatchCommandDTO matchDto) {
        Player currentPlayer = playerService.getSessionPlayer();
        FootballMatch footballMatch = FootballMatch.builder()
                .title(matchDto.getTitle())
                .location(matchDto.getLocation())
                .startDate(matchDto.getStartDate())
                .description(matchDto.getDescription())
                .numberOfPlayers(matchDto.getNumberOfPlayers())
                .participants(new ArrayList<>())
                .owner(currentPlayer)
                .build();
        return footballMatchRepository.save(footballMatch);
    }

    public void deleteMatch(Long id) {
        footballMatchRepository.deleteById(id);
    }

    public void updateMatch(FootballMatch match) {
        footballMatchRepository.save(match);
    }

    public boolean hasUserJoinedTheMatch(FootballMatch footballMatch, Player player) {
        return footballMatch.getParticipants().stream().anyMatch(participant -> participant.getId().equals(player.getId()));
    }
}
