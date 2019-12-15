package my.sport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import my.sport.dto.UserDto;
import my.sport.model.Player;
import my.sport.repository.PlayerRepository;

public class PlayerServiceImpl implements PlayerService{

	@Autowired
	private PlayerRepository<Player> playerRepository;
	
	@Transactional
	public List<Player> findPlayerByFirstName(String firstName) {
		return playerRepository.findByFirstName(firstName);
	}
	
	@Override
	@Transactional
	public List<Player> getAllPlayers() {
		return (List<Player>) playerRepository.findAll();
	}
	
	@Override
	@Transactional
	public Player getPlayerById(Long id) {
		return playerRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public boolean add(Player player) {
		return playerRepository.save(player) != null;
	}
	
	@Override
	@Transactional
	public Player registerNewPlayerAccount(UserDto userDto) {
		Player newPlayer = new Player();
		newPlayer.setEmail(userDto.getEmail());
		newPlayer.setFirstName(userDto.getFirstName());
		newPlayer.setLastName(userDto.getLastName());
		return playerRepository.save(newPlayer);
	}

	@Override
	@Transactional
	public void deletePlayer(Long id) {
		playerRepository.deleteById(id);
	}
}
