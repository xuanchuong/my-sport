package my.sport.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import my.sport.model.Player;

@Data
@NoArgsConstructor
public class FootballMatchDto {
	private Player owner;
	private Date startDate;
	private String location;
	private String title;
	private String description;
}
