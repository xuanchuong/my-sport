package my.sport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.sport.model.Player;

@Repository
public interface PlayerRepository<P> extends JpaRepository<Player, Long>{
	List<Player> findByFirstName(String firstName);
}
