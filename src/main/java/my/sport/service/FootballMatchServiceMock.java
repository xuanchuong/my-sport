package my.sport.service;

import java.util.ArrayList;
import java.util.List;

import my.sport.model.FootballMatch;

public class FootballMatchServiceMock implements FootballMatchService{
	
	private static final List<FootballMatch> FOOTBALL_MATCHES;
	
	static {
		FOOTBALL_MATCHES = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			FootballMatch footballMatch = new FootballMatch();
			footballMatch.setTitle("ramdom match " + i);
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
}
