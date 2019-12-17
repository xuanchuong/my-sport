package my.sport.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import my.sport.dto.UserDto;
import my.sport.model.Player;
import my.sport.repository.PlayerRepository;
import my.sport.repository.RoleRepository;

public class PlayerServiceImpl implements PlayerService {
	@Autowired
	private PlayerRepository<Player> playerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

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
		Player player = new Player();
		player.setEmail(userDto.getEmail());
		player.setFirstName(userDto.getFirstName());
		player.setLastName(userDto.getLastName());
		player.setPassword(passwordEncoder.encode(userDto.getPassword()));
		player.setRoles(Arrays.asList(roleRepository.findByName("PLAYER")));
		return playerRepository.save(player);
	}

	@Override
	@Transactional
	public void deletePlayer(Long id) {
		playerRepository.deleteById(id);
	}

	@Override
	public Player getSessionPlayer() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		return playerRepository.findUserByEmail(email);
	}
}
