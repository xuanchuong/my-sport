package endpoint;

import application.service.PlayerService;
import domain.entity.Player;
import dto.UserOutDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import mapper.UserMapper;
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

	@DeleteMapping
	public ResponseEntity<HttpStatus> deletePlayer(@RequestParam String email) {
		if (playerService.getPlayerByEmail(email) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		playerService.deletePlayer(email);
		return ResponseEntity.status(HttpStatus.OK).build();
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
