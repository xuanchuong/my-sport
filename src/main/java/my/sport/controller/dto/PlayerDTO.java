package my.sport.controller.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import my.sport.domain.entity.Role;

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
