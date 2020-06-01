package my.sport.data.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class JpaFootballMatch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="owner_id", nullable=false)
	private JpaPlayer owner;
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "location")
	private String location;
	@Column
	private String title;
	@Column
	private String description;
	@Column
	private int numberOfPlayers;
	
	@ManyToMany
	@JoinTable(name="match_players")
	private List<JpaPlayer> paticipants;
}
