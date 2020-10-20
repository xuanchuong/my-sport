package service;


import domain.entity.FootballMatch;
import domain.entity.Player;
import domain.repository.FootballMatchRepository;
import domain.vo.CreateFootballMatchCommand;
import domain.vo.MatchStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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

    @Transactional
    public FootballMatch createNewMatch(CreateFootballMatchCommand matchDto) {
        if (!matchDto.isValid()) {
            throw new IllegalArgumentException("matchDto's fields are missing");
        }
        Player currentPlayer = playerService.getSessionPlayer();
        FootballMatch footballMatch = FootballMatch.builder()
                .title(matchDto.getTitle())
                .location(matchDto.getLocation())
                .startDate(matchDto.getStartDate())
                .description(matchDto.getDescription())
                .numberOfPlayers(matchDto.getNumberOfPlayers())
                .participants(Collections.singletonList(currentPlayer))
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

    @Transactional
    public void leaveTheMatch(FootballMatch footballMatch, Player player) {
        if (!hasUserJoinedTheMatch(footballMatch, player)) {
            throw new IllegalArgumentException("the user has not joined the match");
        }
        footballMatch.getParticipants().stream()
                .filter(participant -> participant.getId().equals(player.getId()))
                .findFirst().ifPresent(participant -> footballMatch.getParticipants().remove(participant));
        updateMatch(footballMatch);
    }

    @Transactional
    public void cancelJoinRequest(FootballMatch footballMatch, Player player) {
        if (!hasPendingRequest(footballMatch, player)) {
            throw new IllegalArgumentException("the user have not requested to join the match");
        }
        footballMatch.getPendingPlayer().stream()
                .filter(pendingPlayer -> pendingPlayer.getId().equals(player.getId()))
                .findFirst().ifPresent(participant -> footballMatch.getPendingPlayer().remove(participant));
        updateMatch(footballMatch);
    }

    public boolean hasUserJoinedTheMatch(FootballMatch footballMatch, Player player) {
        if (player == null) {
            return false;
        }
        return footballMatch.getParticipants().stream().anyMatch(participant -> participant.getId().equals(player.getId()));
    }

    @Transactional
    public void cancel(FootballMatch footballMatch, Player currentPlayer) {
        if (!isMatchOwner(footballMatch, currentPlayer)) {
            return;
        }
        FootballMatch updatingMatch = footballMatch.toBuilder().matchStatus(MatchStatus.CANCEL).build();
        updateMatch(updatingMatch);
    }

    private boolean isMatchOwner(FootballMatch footballMatch, Player currentPlayer) {
        return footballMatch.getOwner().getId().equals(currentPlayer.getId());
    }

    @Transactional
    public void joinTheMatch(FootballMatch footballMatch, Player player) {
        if (this.hasUserJoinedTheMatch(footballMatch, player)) {
            throw new IllegalArgumentException("the user already joined the match");
        }
        if (hasPendingRequest(footballMatch, player)) {
            throw new IllegalArgumentException("the user already requested to join the match");
        }
        footballMatch.getPendingPlayer().add(player);
        updateMatch(footballMatch);
    }

    public boolean hasPendingRequest(FootballMatch footballMatch, Player player) {
        if (player == null) {
            return false;
        }
        return footballMatch.getPendingPlayer().stream().anyMatch(participant -> participant.getId().equals(player.getId()));
    }
}
