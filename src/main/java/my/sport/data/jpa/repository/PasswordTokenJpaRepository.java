package my.sport.data.jpa.repository;

import my.sport.data.jpa.entity.PasswordResetTokenJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordTokenJpaRepository extends JpaRepository<PasswordResetTokenJpa, Long> {}
