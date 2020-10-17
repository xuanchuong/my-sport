package domain.repository;

import domain.entity.PasswordResetToken;

public interface PasswordTokenRepository {

    void save(PasswordResetToken passwordResetToken);
}
