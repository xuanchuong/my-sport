package my.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.sport.model.Player;
import my.sport.service.PlayerService;

@Controller
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	private PlayerService playerService;
	
	@GetMapping("/detail")
	public String detailPlayer(@RequestParam(value = "id", required = true) String id, Model model) {
		Long playerId = Long.valueOf(id);
		Player player = playerService.getPlayerById(playerId);
		model.addAttribute("player", player);
		return "playerInfo";
	}
}
