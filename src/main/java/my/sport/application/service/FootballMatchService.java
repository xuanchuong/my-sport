package my.sport.application.service;

import my.sport.domain.entity.FootballMatch;
import my.sport.domain.entity.Player;
import my.sport.domain.repository.FootballMatchRepository;
import my.sport.domain.vo.MatchStatus;
import my.sport.rest.dto.CreateFootballMatchCommandDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FootballMatchService {

    FootballMatchRepository footballMatchRepository;

    PlayerService playerService;

    public FootballMatchService(FootballMatchRepository footballMatchRepository, PlayerService playerService) {
        this.footballMatchRepository = footballMatchRepository;
        this.playerService = playerService;
    }

    public List<FootballMatch> getAllMatch() {
        return footballMatchRepository.findAll().stream().filter(match -> match.getMatchStatus() == MatchStatus.READY)
                .collect(Collectors.toList());
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
                .matchStatus(MatchStatus.READY)
                .build();
        return footballMatchRepository.save(footballMatch);
    }

    public void deleteMatch(Long id) {
        footballMatchRepository.deleteById(id);
    }

    public void updateMatch(FootballMatch match) {
        footballMatchRepository.save(match);
    }

    public void leaveTheMatch(FootballMatch footballMatch, Player player) {
        footballMatch.getParticipants().stream()
                .filter(participant -> participant.getId().equals(player.getId()))
                .findFirst().ifPresent(participant -> footballMatch.getParticipants().remove(participant));
        updateMatch(footballMatch);
    }

    public boolean hasUserJoinedTheMatch(FootballMatch footballMatch, Player player) {
        return footballMatch.getParticipants().stream().anyMatch(participant -> participant.getId().equals(player.getId()));
    }

    public boolean cancel(FootballMatch footballMatch, Player currentPlayer) {
        if (!isMatchOwner(footballMatch, currentPlayer)) {
            return false;
        }
        FootballMatch updatingMatch = footballMatch.toBuilder().matchStatus(MatchStatus.CANCEL).build();
        updateMatch(updatingMatch);
        return true;
    }

    private boolean isMatchOwner(FootballMatch footballMatch, Player currentPlayer) {
        return footballMatch.getOwner().getId().equals(currentPlayer.getId());
    }
}
