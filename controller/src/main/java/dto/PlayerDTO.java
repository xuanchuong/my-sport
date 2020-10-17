package dto;

import domain.entity.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerDTO {

	Long id;

	String firstName;

	String lastName;

	String email;

	String phoneNumber;

	String description;

	String password;

	List<Role> roles;
}
