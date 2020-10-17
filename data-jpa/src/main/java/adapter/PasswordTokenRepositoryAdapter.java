package adapter;

import domain.entity.PasswordResetToken;
import domain.repository.PasswordTokenRepository;
import lombok.AllArgsConstructor;
import mapper.PasswordTokenMapper;
import repository.PasswordTokenJpaRepository;

@AllArgsConstructor
public class PasswordTokenRepositoryAdapter implements PasswordTokenRepository {

    private final PasswordTokenJpaRepository passwordTokenJpaRepository;
    private final PasswordTokenMapper mapper;

    @Override
    public void save(PasswordResetToken passwordResetToken) {
        passwordTokenJpaRepository.save(mapper.map(passwordResetToken));
    }
}
