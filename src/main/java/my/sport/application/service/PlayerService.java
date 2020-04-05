package my.sport.application.service;

import lombok.AllArgsConstructor;
import my.sport.domain.entity.Player;
import my.sport.domain.repository.PlayerRepository;
import my.sport.domain.repository.RoleRepository;
import my.sport.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@AllArgsConstructor
public class PlayerService {

	private PlayerRepository playerRepository;
	private PasswordEncoder passwordEncoder;
	private RoleRepository roleRepository;

	@Transactional
	public List<Player> findPlayerByFirstName(String firstName) {
		return playerRepository.findByFirstName(firstName);
	}

	
	@Transactional
	public List<Player> getAllPlayers() {
		return (List<Player>) playerRepository.findAll();
	}

	
	@Transactional
	public Player getPlayerById(Long id) {
		return playerRepository.findById(id).orElse(null);
	}

	
	@Transactional
	public Player getPlayerByEmail(String email) {
		return playerRepository.findUserByEmail(email);
	}

	
	@Transactional
	public boolean add(Player player) {
		return playerRepository.save(player) != null;
	}

	
	@Transactional
	public Player registerNewPlayerAccount(UserDto userDto) {
		Player player = new Player();
		player.setEmail(userDto.getEmail());
		player.setFirstName(userDto.getFirstName());
		player.setLastName(userDto.getLastName());
		player.setPassword(passwordEncoder.encode(userDto.getPassword()));
		player.setRoles(Arrays.asList(roleRepository.findByName("PLAYER")));
		return (Player) playerRepository.save(player);
	}

	
	@Transactional
	public void deletePlayer(Long id) {
		playerRepository.deleteById(id);
	}

	
	public Player getSessionPlayer() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		return playerRepository.findUserByEmail(email);
	}
}
