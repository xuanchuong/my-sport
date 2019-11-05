package my.sport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import my.sport.model.Player;
import my.sport.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	private PlayerService playerService;
	
	@GetMapping
	public List<Player> getAllPlayers() {
		return playerService.getAllPlayers();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public HttpStatus insertPersone(@RequestBody Player player) {
		return playerService.add(player) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public HttpStatus deletePlayer(@PathVariable Long id) {
		playerService.deletePlayer(id);
		return HttpStatus.NO_CONTENT;
	}
}
