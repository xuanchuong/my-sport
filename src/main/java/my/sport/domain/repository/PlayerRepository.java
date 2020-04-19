package my.sport.domain.repository;

import my.sport.domain.entity.Player;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository {

	Player findUserByEmail(String email);

	Optional<Player> findById(Long id);

	Object save(Player player);

	void deleteByEmail(String email);
}
