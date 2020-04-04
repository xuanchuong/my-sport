package my.sport.controller;

import my.sport.model.Player;
import my.sport.rest.dto.UserOutDTO;
import my.sport.rest.mapper.UserMapper;
import my.sport.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/player", "/rest/api/v1/user"})
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/detail")
	public String detailPlayer(@RequestParam(value = "id", required = true) String id, Model model) {
		Long playerId = Long.valueOf(id);
		Player player = playerService.getPlayerById(playerId);
		model.addAttribute("player", player);
		return "playerInfo";
	}

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
}
