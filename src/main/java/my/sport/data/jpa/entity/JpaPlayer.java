package my.sport.data.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "player")
@AllArgsConstructor
@NoArgsConstructor
public class JpaPlayer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "player_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(unique = true)
	private String email;

	@Column
	private String description;

	@Column
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<JpaRole> roles;
}
