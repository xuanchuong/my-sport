package my.sport.domain.vo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.codehaus.plexus.util.StringUtils;

import java.util.function.BooleanSupplier;
import java.util.stream.Stream;

@Builder
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CreateUserCommand {

    String firstName;
    String lastName;
    String password;
    String email;

    public boolean isValid() {
        return Stream.of(
                isNotBlack(this.firstName),
                isNotBlack(this.lastName),
                isNotBlack(this.email),
                isNotBlack(this.password)
        ).allMatch(BooleanSupplier::getAsBoolean);
    }

    BooleanSupplier isNotBlack(String input) {
        return () -> StringUtils.isNotBlank(input);
    }
}
