package my.sport.domain.repository;

import my.sport.domain.entity.Player;

import java.util.Optional;

public interface PlayerRepository {

	Player findUserByEmail(String email);

	Optional<Player> findById(Long id);

	Player save(Player player);

	void deleteByEmail(String email);
}
