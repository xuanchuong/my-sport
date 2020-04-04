package my.sport.controller;

import my.sport.service.FootballMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/dashboard", "/"})
public class DashBoardController {
	@Autowired
	private FootballMatchService footballMatchService;

	@GetMapping
	public String myContact(Model model) {
		model.addAttribute("availableMatches", footballMatchService.getAllAvailableFootballMatch());
		return "dashboard";
	}
}
