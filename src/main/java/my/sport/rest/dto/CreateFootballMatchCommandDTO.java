package my.sport.rest.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CreateFootballMatchCommandDTO {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime startDate;
    @NotEmpty
    String location;
    @NotEmpty
    String title;
    @NotEmpty
    String description;
    @Min(10)
    int numberOfPlayers;

}
