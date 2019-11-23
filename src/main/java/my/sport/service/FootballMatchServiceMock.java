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
			FOOTBALL_MATCHES.add(footballMatch);
		}
	}

	@Override
	public List<FootballMatch> getAllAvailableFootballMatch() {
		return FOOTBALL_MATCHES;
	}
}
