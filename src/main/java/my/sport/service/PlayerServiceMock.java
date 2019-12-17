package my.sport.service;

import java.util.Arrays;
import java.util.List;

import my.sport.dto.UserDto;
import my.sport.model.Player;

public class PlayerServiceMock implements PlayerService {

	private static Player player1 = new Player();
	private static Player player2 = new Player();
	private static Player player3 = new Player();
	private static final List<Player> PLAYERS_MOCK = Arrays.asList(player1, player2, player3);

	@Override
	public List<Player> getAllPlayers() {
		return PLAYERS_MOCK;
	}

	@Override
	public Player getPlayerById(Long id) {

		for (Player player : PLAYERS_MOCK) {
			if (player.getId().equals(id)) {
				return player;
			}

		}
		return null;
	}

	@Override
	public boolean add(Player player) {
		return PLAYERS_MOCK.add(player);
	}

	@Override
	public void deletePlayer(Long id) {

	}
	
	@Override
	public Player registerNewPlayerAccount(UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
