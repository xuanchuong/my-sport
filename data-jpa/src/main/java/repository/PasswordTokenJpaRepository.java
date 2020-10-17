package repository;

import entity.PasswordResetTokenJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordTokenJpaRepository extends JpaRepository<PasswordResetTokenJpa, Long> {}
