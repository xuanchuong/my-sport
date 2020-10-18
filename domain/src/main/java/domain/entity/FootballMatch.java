package domain.entity;

import domain.vo.MatchStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder(toBuilder = true)
@Getter
public class FootballMatch {

	private final Long id;
	private final Player owner;
	@Builder.Default
	private final Set<Player> pendingParticipants = new HashSet<>();
	private final LocalDateTime startDate;
	private final String location;
	private final String title;
	private final String description;
	private final int numberOfPlayers;
	@Builder.Default
	private final MatchStatus matchStatus = MatchStatus.READY;
	private final List<Player> participants;
}
