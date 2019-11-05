package my.sport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import my.sport.model.Player;
import my.sport.repository.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;
	
	@Transactional
	public List<Player> findPlayerByFirstName(String firstName) {
		return playerRepository.findByFirstName(firstName);
	}
	
	@Transactional
	public List<Player> getAllPlayers() {
		return (List<Player>) playerRepository.findAll();
	}
	
	@Transactional
	public boolean add(Player player) {
		return playerRepository.save(player) != null;
	}
}
