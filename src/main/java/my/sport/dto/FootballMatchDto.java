package my.sport.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import my.sport.model.Player;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
public class FootballMatchDto {
	private Player owner;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@NotNull
	@NotEmpty
	private String location;
	@NotNull
	@NotEmpty
	private String title;
	@NotNull
	@NotEmpty
	private String description;
	@NotNull
	private int numberOfPlayers;
	@NotNull
	private List<Player> paticipants;
}
