package my.sport.rest.dto;

import lombok.Data;
import my.sport.domain.validator.PasswordMatches;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@PasswordMatches
public class CreateUserCommandDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String password;

    @NotBlank
    private String matchingPassword;

    @NotBlank
    @Email
    private String email;
}
