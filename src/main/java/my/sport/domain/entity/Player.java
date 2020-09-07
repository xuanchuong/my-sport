package my.sport.domain.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

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
}
