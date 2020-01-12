package my.sport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import my.sport.dto.FootballMatchDto;
import my.sport.model.FootballMatch;
import my.sport.model.Player;
import my.sport.repository.FootballMatchRepository;

public class FootballMatchServiceImpl implements FootballMatchService{
	@Autowired
	private FootballMatchRepository footballMatchRepository;
	
	@Autowired
	private PlayerService playerService;

	@Override
	public List<FootballMatch> getAllAvailableFootballMatch() {
		return footballMatchRepository.findAll();
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
