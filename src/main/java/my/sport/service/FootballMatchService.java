package my.sport.service;

import java.util.List;

import my.sport.dto.FootballMatchDto;
import my.sport.model.FootballMatch;

public interface FootballMatchService {

	List<FootballMatch> getAllAvailableFootballMatch();
	
	FootballMatch getMatchById(Long id);
	
	FootballMatch createNewMatch(FootballMatchDto matchDto);
	
	void deleteMatch(Long id);
	
	void updateMatch(FootballMatch match);
}
