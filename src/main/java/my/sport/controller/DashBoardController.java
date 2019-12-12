package my.sport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.sport.controller.rest.PlayerRestController;
import my.sport.header.HeadNavigator;
import my.sport.model.Player;
import my.sport.service.FootballMatchService;
import my.sport.service.HeaderService;

@Controller
@RequestMapping(value = {"/dashboard", "/"})
public class DashBoardController {

	@Autowired
	private PlayerRestController playerRestController;
	@Autowired
	private FootballMatchService footballMatchService;

	@GetMapping
	public String myContact(Model model) {
		model.addAttribute(HeaderService.HEADER_ATTR, HeaderService.getNavigatoritems());
		model.addAttribute(HeaderService.SELECTED_ITEM_ATTR, HeadNavigator.HOME);
		model.addAttribute("availableMatches", footballMatchService.getAllAvailableFootballMatch());
		return "dashboard";
	}

	@PostMapping
	public String search(@RequestParam(value = "id", required = false) String id, Model model) {
		List<Player> allPlayers = playerRestController.getAllPlayers();
		model.addAttribute("searchResult", allPlayers);
		return "dashboard";
	}
}
