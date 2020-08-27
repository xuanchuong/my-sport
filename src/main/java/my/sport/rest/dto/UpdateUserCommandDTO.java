package my.sport.rest.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class UpdateUserCommandDTO {
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
}
