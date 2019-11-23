package my.sport.service;

import java.util.List;

import my.sport.model.Player;

public interface PlayerService {

	public List<Player> getAllPlayers();

	public Player getPlayerById(Long id);

	public boolean add(Player player);

	public void deletePlayer(Long id);
}
