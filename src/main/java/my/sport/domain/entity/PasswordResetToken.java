package my.sport.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Calendar;
import java.util.Date;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PasswordResetToken {
    String token;
    Player player;
    Date expiryDate;
    static final int EXPIRATION = 60 * 24;

    public PasswordResetToken(String token, Player player) {
        this.token = token;
        this.player = player;
        this.expiryDate = calculateExpiryDate();
    }

    private Date calculateExpiryDate() {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, PasswordResetToken.EXPIRATION);
        return new Date(cal.getTime().getTime());
    }
}
