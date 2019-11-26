package my.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.sport.model.FootballMatch;
import my.sport.service.FootballMatchService;

@Controller
@RequestMapping("/match")
public class MatchController {

	@Autowired
	private FootballMatchService matchService;
	@GetMapping
	public String createMatch() {
		return "createMatchForm";
	}
	
	@GetMapping("/detail")
	public String getMatchDetail(@RequestParam String id, Model model) {
		FootballMatch footballMatch = matchService.getMatchById(Long.valueOf(id));
		model.addAttribute("match", footballMatch);
		return "matchDetail";
	}
	
	@PostMapping
	public String creatingMatch() {
		return "createMatchConfirm";
	}
}
