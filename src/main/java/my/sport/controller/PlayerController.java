package my.sport.controller;

import my.sport.model.Player;
import my.sport.rest.dto.UserOutDTO;
import my.sport.rest.mapper.UserMapper;
import my.sport.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/v1/user")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private UserMapper userMapper;

	@GetMapping("/{id}")
	public ResponseEntity<UserOutDTO> readUser(@PathVariable("id") String id) {
		Long playerId = Long.valueOf(id);
		Player player = playerService.getPlayerById(playerId);
		if (player == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		UserOutDTO userOutDTO = userMapper.map(player);
		return ResponseEntity.ok(userOutDTO);
	}

	@GetMapping
	public ResponseEntity<UserOutDTO> readUserByEmail(@RequestHeader("email") String email) {
		Player player = playerService.getPlayerByEmail(email);
		if (player == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		UserOutDTO userOutDTO = userMapper.map(player);
		return ResponseEntity.ok(userOutDTO);
	}

	@DeleteMapping(value = "/{id}")
	public HttpStatus deletePlayer(@PathVariable Long id) {
		playerService.deletePlayer(id);
		return HttpStatus.NO_CONTENT;
	}
}
