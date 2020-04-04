package my.sport.service;

import java.util.List;

import my.sport.dto.UserDto;
import my.sport.domain.entity.Player;

public interface PlayerService {

	public List<Player> getAllPlayers();

	public Player getPlayerById(Long id);

	public Player getPlayerByEmail(String email);

	public boolean add(Player player);

	public void deletePlayer(Long id);
	
	public Player registerNewPlayerAccount(UserDto userDto) ;
	
	public Player getSessionPlayer();
}
