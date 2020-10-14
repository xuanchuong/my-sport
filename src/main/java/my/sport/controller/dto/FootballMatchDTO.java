package my.sport.controller.dto;

import lombok.Getter;
import lombok.Setter;
import my.sport.domain.vo.MatchStatus;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class FootballMatchDTO {

	private Long id;
	private PlayerDTO owner;
	private LocalDateTime startDate;
	private String location;
	private String title;
	private String description;
	private int numberOfPlayers;
	private MatchStatus matchStatus;
	private List<PlayerDTO> participants;
}
