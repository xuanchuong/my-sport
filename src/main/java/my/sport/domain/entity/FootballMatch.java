package my.sport.domain.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Builder
@Getter
public class FootballMatch {
	private final Long id;
	private final Player owner;
	private final Date startDate;
	private final String location;
	private final String title;
	private final String description;
	private final int numberOfPlayers;
	private final List<Player> paticipants;
}
