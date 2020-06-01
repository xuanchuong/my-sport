package my.sport.rest.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class FootballMatchDto {
	private Long id;
	private Long ownerId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	private String location;
	private String title;
	private String description;
	private int numberOfPlayers;
	private List<Long> paticipantIds;
}
