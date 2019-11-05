package my.sport.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import my.sport.model.Player;

public interface PlayerRepository extends CrudRepository<Player, Long>{
	List<Player> findByFirstName(String firstName);
}
