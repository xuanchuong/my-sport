package my.sport.rest.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CreateFootballMatchCommandDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date startDate;
    @NotEmpty
    String location;
    @NotEmpty
    String title;
    @NotEmpty
    String description;
    int numberOfPlayers;

}
