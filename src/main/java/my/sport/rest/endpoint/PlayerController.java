package my.sport.rest.endpoint;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import my.sport.application.service.PlayerService;
import my.sport.domain.entity.Player;
import my.sport.rest.dto.CreateUserCommandDTO;
import my.sport.rest.dto.UserOutDTO;
import my.sport.rest.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
