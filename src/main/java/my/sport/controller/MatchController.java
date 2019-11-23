package my.sport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/match")
public class MatchController {

	@GetMapping
	public String createMatch() {
		return "createMatchForm";
	}
	
	@PostMapping
	public String creatingMatch() {
		return "createMatchConfirm";
	}
}
