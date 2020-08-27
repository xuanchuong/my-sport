package my.sport.data.jpa.adapter;

import lombok.AllArgsConstructor;
import my.sport.data.jpa.mapper.PasswordTokenMapper;
import my.sport.data.jpa.repository.PasswordTokenJpaRepository;
import my.sport.domain.entity.PasswordResetToken;
import my.sport.domain.repository.PasswordTokenRepository;

@AllArgsConstructor
public class PasswordTokenRepositoryAdapter implements PasswordTokenRepository {

    private final PasswordTokenJpaRepository passwordTokenJpaRepository;
    private final PasswordTokenMapper mapper;

    @Override
    public void save(PasswordResetToken passwordResetToken) {
        passwordTokenJpaRepository.save(mapper.map(passwordResetToken));
    }
}
