package my.sport.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import my.sport.validator.PasswordMatches;

@Data
@PasswordMatches
public class UserDto {

	@NotNull
	@NotEmpty
	private String firstName;
	@NotNull
	@NotEmpty
	private String lastName;
	@NotNull
	@NotEmpty
	private String password;
	private String matchingPassword;

	@NotNull
	@NotEmpty
	@Email
	private String email;
}
