package my.sport.application.service;

import my.sport.domain.entity.FootballMatch;
import my.sport.domain.entity.Player;
import my.sport.domain.repository.FootballMatchRepository;
import my.sport.dto.FootballMatchDto;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class FootballMatchService {
    FootballMatchRepository footballMatchRepository;
    @PersistenceContext
    EntityManager entityManager;

    PlayerService playerService;

    public FootballMatchService(FootballMatchRepository footballMatchRepository, PlayerService playerService) {
        this.footballMatchRepository = footballMatchRepository;
        this.playerService = playerService;
    }

    public List<FootballMatch> getAllAvailableFootballMatch() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FootballMatch> query = criteriaBuilder.createQuery(FootballMatch.class);
        Root<FootballMatch> scheduledEventRoot = query.from(FootballMatch.class);
        Predicate matchPredicate = criteriaBuilder.greaterThanOrEqualTo(scheduledEventRoot.get("startDate"), criteriaBuilder.currentDate());
        query.select(scheduledEventRoot)
                .where(matchPredicate);
        return entityManager.createQuery(query).getResultList();
    }

    public FootballMatch getMatchById(Long id) {
        return footballMatchRepository.findById(id).orElse(null);
    }

    public FootballMatch createNewMatch(FootballMatchDto matchDto) {
        FootballMatch footballMatch = new FootballMatch();
        footballMatch.setTitle(matchDto.getTitle());
        footballMatch.setLocation(matchDto.getLocation());
        footballMatch.setStartDate(matchDto.getStartDate());
        footballMatch.setDescription(matchDto.getDescription());
        footballMatch.setNumberOfPlayers(matchDto.getNumberOfPlayers());
        Player currentPlayer = playerService.getSessionPlayer();
        footballMatch.setOwner(currentPlayer);
        return footballMatchRepository.save(footballMatch);
    }

    public void deleteMatch(Long id) {
        footballMatchRepository.deleteById(id);
    }

    public void updateMatch(FootballMatch match) {
        footballMatchRepository.save(match);
    }
}
