package my.sport.rest.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import my.sport.domain.vo.MatchStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FootballMatchOut {
	Long id;
	UserOutDTO owner;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date startDate;
	String location;
	String title;
	String description;
	int numberOfPlayers;
	MatchStatus matchStatus;
	List<UserOutDTO> participants;
}
