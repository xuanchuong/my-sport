package my.sport.rest.endpoint;

import my.sport.application.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import my.sport.domain.entity.Player;

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
