package my.sport.data.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.sport.domain.vo.MatchStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "football_match")
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
	@Enumerated
	@Column (name = "match_status")
	private MatchStatus matchStatus;
	@ManyToMany
	@JoinTable(name="match_players")
	private List<JpaPlayer> paticipants;
}
