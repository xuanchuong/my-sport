package my.sport.rest.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CreateFootballMatchCommandDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date startDate;
    @NotBlank
    String location;
    @NotBlank
    String title;
    @NotBlank
    String description;
    int numberOfPlayers;

}
