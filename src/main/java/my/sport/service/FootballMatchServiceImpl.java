package my.sport.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import my.sport.dto.FootballMatchDto;
import my.sport.model.FootballMatch;
import my.sport.model.Player;
import my.sport.repository.FootballMatchRepository;

public class FootballMatchServiceImpl implements FootballMatchService{
	@Autowired
	private FootballMatchRepository footballMatchRepository;
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	private PlayerService playerService;

	@Override
	public List<FootballMatch> getAllAvailableFootballMatch() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<FootballMatch> query = criteriaBuilder.createQuery(FootballMatch.class);
		Root<FootballMatch> scheduledEventRoot = query.from(FootballMatch.class);
		Predicate matchPredicate = criteriaBuilder.greaterThanOrEqualTo(scheduledEventRoot.get("startDate"), criteriaBuilder.currentDate());
		query.select(scheduledEventRoot)
			.where(matchPredicate);
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public FootballMatch getMatchById(Long id) {
		return footballMatchRepository.findById(id).orElse(null);
	}

	@Override
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
	
	@Override
	public void deleteMatch(Long id) {
		footballMatchRepository.deleteById(id);
	}
	
	@Override
	public void updateMatch(FootballMatch match) {
		footballMatchRepository.save(match);
	}
}
