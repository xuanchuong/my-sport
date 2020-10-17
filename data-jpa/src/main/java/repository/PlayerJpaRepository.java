package repository;

import entity.JpaPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerJpaRepository extends JpaRepository<JpaPlayer, Long>{
    JpaPlayer findUserByEmail(String email);
    void deleteByEmail(String email);
}
