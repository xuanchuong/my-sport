package my.sport.application.service;

import lombok.AllArgsConstructor;
import my.sport.domain.entity.Player;
import my.sport.domain.repository.PlayerRepository;
import my.sport.domain.repository.RoleRepository;
import my.sport.dto.UserDto;
import my.sport.rest.dto.CreateUserCommandDTO;
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
	public Player getPlayerById(Long id) {
		return playerRepository.findById(id).orElse(null);
	}

	
	@Transactional
	public Player getPlayerByEmail(String email) {
		return playerRepository.findUserByEmail(email);
	}

	
	@Transactional
	public Player add(CreateUserCommandDTO createUserCommandDTO) {
		Player player = new Player();
		player.setEmail(createUserCommandDTO.getEmail());
		player.setFirstName(createUserCommandDTO.getFirstName());
		player.setLastName(createUserCommandDTO.getLastName());
		player.setPassword(passwordEncoder.encode(createUserCommandDTO.getPassword()));
		player.setRoles(Arrays.asList(roleRepository.findByName("PLAYER")));
		playerRepository.save(player);
		return player;
	}
	
	@Transactional
	public void deletePlayer(String email) {
		playerRepository.deleteByEmail(email);
	}

	
	public Player getSessionPlayer() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		return playerRepository.findUserByEmail(email);
	}
}
