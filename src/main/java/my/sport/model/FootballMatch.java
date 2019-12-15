package my.sport.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FootballMatch {
	private Player owner;
	private List<Player> players;
	private Date startDate;
	private String location;
	private String title;
	private String description;
	private Long id;
}
