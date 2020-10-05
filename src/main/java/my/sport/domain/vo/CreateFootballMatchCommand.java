package my.sport.domain.vo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.codehaus.plexus.util.StringUtils;

import java.util.Date;
import java.util.function.BooleanSupplier;
import java.util.stream.Stream;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class CreateFootballMatchCommand {
    Date startDate;
    String location;
    String title;
    String description;
    int numberOfPlayers;

    public boolean isValid() {
        return Stream.of(
                isNotBlack(this.location),
                isNotBlack(this.title),
                isNotBlack(this.description),
                () -> this.numberOfPlayers > 5,
                () -> this.startDate != null
        ).allMatch(BooleanSupplier::getAsBoolean);
    }

    BooleanSupplier isNotBlack(String input) {
        return () -> StringUtils.isNotBlank(input);
    }
}
