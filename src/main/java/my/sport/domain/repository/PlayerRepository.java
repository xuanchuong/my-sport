package my.sport.domain.repository;

import my.sport.domain.entity.Player;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository {
	List<Player> findByFirstName(String firstName);
	Player findUserByEmail(String email);
	List<Player> findAll();
	
	Optional<Player> findById(Long id);

	Object save(Player player);

	void deleteById(Long id);
}
