package my.sport.data.jpa.repository;

import my.sport.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerJpaRepository extends JpaRepository<Player, Long>{
    Player findUserByEmail(String email);
}
