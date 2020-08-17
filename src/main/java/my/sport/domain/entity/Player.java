package my.sport.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private String phoneNumber;

	private String description;

	private String password;

	private List<Role> roles;
}
