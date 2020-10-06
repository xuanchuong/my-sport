package my.sport.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import my.sport.application.service.FootballMatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/dashboard", "/"})
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DashBoardController {

	FootballMatchService matchService;

	@GetMapping
	public String getDashBoardView(Model model) {
		model.addAttribute("availableMatches", matchService.getAllMatch());
		return "dashboard";
	}
}
