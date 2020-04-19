package my.sport.rest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Setter
@Getter
@FieldDefaults(level = PRIVATE)
public class UserOutDTO {
    Long id;
    String firstName;
    String lastName;
    String email;
}
