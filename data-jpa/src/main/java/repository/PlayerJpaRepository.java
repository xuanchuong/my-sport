package repository;

import entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerJpaRepository extends JpaRepository<Player, Long>{
    Player findUserByEmail(String email);
    void deleteByEmail(String email);
}
