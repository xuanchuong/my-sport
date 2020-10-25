package domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Objects;

@Builder(toBuilder = true)
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Player {

	Long id;

	String firstName;

	String lastName;

	String email;

	String phoneNumber;

	String description;

	String password;

	List<Role> roles;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Player player = (Player) o;
		return email.equals(player.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}
}
