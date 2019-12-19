package my.sport.service;

import java.util.ArrayList;
import java.util.List;

import my.sport.dto.FootballMatchDto;
import my.sport.model.FootballMatch;

public class FootballMatchServiceMock implements FootballMatchService{
	
	private static final List<FootballMatch> FOOTBALL_MATCHES;
	
	static {
		FOOTBALL_MATCHES = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			FootballMatch footballMatch = new FootballMatch();
			footballMatch.setTitle("Lorem Ipsum is simply dummy text " + i);
			footballMatch.setDescription(
					"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s");
			footballMatch.setId(Long.valueOf(i));
			FOOTBALL_MATCHES.add(footballMatch);
		}
	}

	@Override
	public List<FootballMatch> getAllAvailableFootballMatch() {
		return FOOTBALL_MATCHES;
	}
	
	@Override
	public FootballMatch getMatchById(Long id) {
		for (FootballMatch footballMatch : FOOTBALL_MATCHES) {
			if (footballMatch.getId().equals(id)) {
				return footballMatch;
			}
		}
		return null;
	}
	
	@Override
	public FootballMatch createNewMatch(FootballMatchDto matchDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void deleteMatch(Long id) {
		// TODO Auto-generated method stub
	}
}
