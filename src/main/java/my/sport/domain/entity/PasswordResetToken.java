package my.sport.domain.entity;

import lombok.Getter;

import java.util.Calendar;
import java.util.Date;

@Getter
public class PasswordResetToken {
    private String token;
    private Player player;
    private Date expiryDate;
    private static final int EXPIRATION = 60 * 24;

    public PasswordResetToken(String token, Player player) {
        this.token = token;
        this.player = player;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
