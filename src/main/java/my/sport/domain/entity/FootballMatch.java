package my.sport.domain.entity;

import lombok.Builder;
import lombok.Getter;
import my.sport.domain.vo.MatchStatus;

import java.time.LocalDateTime;
import java.util.List;

@Builder(toBuilder = true)
@Getter
public class FootballMatch {

	private final Long id;
	private final Player owner;
	private final LocalDateTime startDate;
	private final String location;
	private final String title;
	private final String description;
	private final int numberOfPlayers;
	@Builder.Default
	private final MatchStatus matchStatus = MatchStatus.READY;
	private final List<Player> participants;
}
