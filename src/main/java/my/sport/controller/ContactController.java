package my.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import my.sport.model.Player;
import my.sport.service.FootballMatchService;
import my.sport.service.PlayerService;

@Controller
@RequestMapping(value = {"/contact"})
public class ContactController {

	@Autowired
	private PlayerService playerService;
	@GetMapping
	public String myContact(Model model) {
		Player currentPlayer = playerService.getSessionPlayer();
		model.addAttribute("user", currentPlayer);
		return "contact";
	}
}
