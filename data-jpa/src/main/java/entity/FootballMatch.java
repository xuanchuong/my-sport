package entity;

import domain.vo.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "football_match")
public class FootballMatch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "football_match_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="owner_id", nullable=false)
	private Player owner;
	@Column(name = "start_date")
	private LocalDateTime startDate;
	@Column(name = "location")
	private String location;
	@Column
	private String title;
	@Column
	private String description;
	@Column(name = "number_of_player")
	private int numberOfPlayers;
	@Enumerated
	@Column (name = "match_status")
	private MatchStatus matchStatus;
	@ManyToMany
	@JoinTable(name="match_players")
	private List<Player> participants;
	@ManyToMany
	@JoinTable(name="match_joining_request")
	private Set<Player> pendingPlayer;
}

