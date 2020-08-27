package my.sport.rest.endpoint;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import my.sport.application.service.PlayerService;
import my.sport.domain.entity.Player;
import my.sport.rest.dto.CreateUserCommandDTO;
import my.sport.rest.dto.UpdateUserCommandDTO;
import my.sport.rest.dto.UserOutDTO;
import my.sport.rest.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/rest/api/v1/user")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlayerController {

	PlayerService playerService;
	UserMapper userMapper;

	@GetMapping
	public ResponseEntity<UserOutDTO> readUserByEmail(@RequestParam String email) {
		Player player = playerService.getPlayerByEmail(email);
		if (player == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		UserOutDTO userOutDTO = userMapper.map(player);
		return ResponseEntity.ok(userOutDTO);
	}

	@GetMapping("/get")
	public ResponseEntity<UserOutDTO> getUserById(@RequestParam Long id) {
		Optional<Player> playerOptional = playerService.getPlayerById(id);
		if (!playerOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		UserOutDTO userOutDTO = userMapper.map(playerOptional.get());
		return ResponseEntity.ok(userOutDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<UserOutDTO> createUser(@RequestBody CreateUserCommandDTO createUserCommandDTO) {
		if (playerService.getPlayerByEmail(createUserCommandDTO.getEmail()) != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		Player player = playerService.add(createUserCommandDTO);
		UserOutDTO userOutDTO = userMapper.map(player);
		return ResponseEntity.status(HttpStatus.CREATED).body(userOutDTO);
	}

	@DeleteMapping
	public ResponseEntity<HttpStatus> deletePlayer(@RequestParam String email) {
		if (playerService.getPlayerByEmail(email) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		playerService.deletePlayer(email);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PutMapping("/update")
	public ResponseEntity<UserOutDTO> update(@RequestBody UpdateUserCommandDTO updateUserCommandDTO) {
		Player acceptedPlayer = playerService.updatePlayer(updateUserCommandDTO);
		UserOutDTO userOutDTO = userMapper.map(acceptedPlayer);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userOutDTO);
	}

	@PostMapping("/resetPass")
	public ResponseEntity<HttpStatus> resetPassword(@RequestParam String email) {
		Player currentPlayer = playerService.getPlayerByEmail(email);
		if (currentPlayer == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		String token = UUID.randomUUID().toString();
		playerService.createPasswordResetTokenForUser(currentPlayer, token);
		playerService.sendEmailResetPass(currentPlayer, token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
}
