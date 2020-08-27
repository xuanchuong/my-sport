package my.sport.domain.repository;

import my.sport.domain.entity.PasswordResetToken;

public interface PasswordTokenRepository {
    void save(PasswordResetToken passwordResetToken);
}
