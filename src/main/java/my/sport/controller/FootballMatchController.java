package my.sport.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import my.sport.dto.FootballMatchDto;
import my.sport.model.FootballMatch;
import my.sport.model.Player;
import my.sport.service.FootballMatchService;

@Controller
@RequestMapping("/match")
public class FootballMatchController {

	@Autowired
	private FootballMatchService matchService;
	private static final String AJAX_HEADER_NAME = "X-Requested-With";
	private static final String AJAX_HEADER_VALUE = "XMLHttpRequest";

	@GetMapping
	public String createMatch(Model model) {
		FootballMatchDto footballMatchDto = new FootballMatchDto();
		Player firstPlayer = new Player();
		Player secondPlayer = new Player();
		firstPlayer.setFirstName("chuong");
		secondPlayer.setFirstName("Huy");
		List<Player> paticipants = Arrays.asList(firstPlayer, secondPlayer);
		footballMatchDto.setPaticipants(paticipants);
		model.addAttribute("match", footballMatchDto);
		return "createMatchForm";
	}

	@GetMapping("/detail")
	public String getMatchDetail(@RequestParam String id, Model model) {
		FootballMatch footballMatch = matchService
				.getMatchById(Long.valueOf(id));
		model.addAttribute("match", footballMatch);
		return "matchDetail";
	}

	@PostMapping("/join")
	public String joinTheMatch(@RequestParam String id) {
		return "dashboard";
	}

	@PostMapping("/delete")
	public ModelAndView deleteMatch(@RequestParam String id) {
		matchService.deleteMatch(Long.valueOf(id));
		return new ModelAndView("dashboard");
	}

	@PostMapping
	public ModelAndView creatingMatch(
			@ModelAttribute("match") @Valid FootballMatchDto matchDto,
			BindingResult resutl, Errors errors, HttpServletRequest request) {
		if (resutl.hasErrors()) {
			return new ModelAndView("createMatchForm", "match", matchDto);
		}
		matchService.createNewMatch(matchDto);
		return new ModelAndView("dashboard");
	}

	@PostMapping("/addItem")
	public String addOrder(@ModelAttribute("match") FootballMatchDto matchDto, HttpServletRequest request, Model model) {
		Player newPlayer = new Player();
		newPlayer.setFirstName("random name");
		matchDto.getPaticipants().add(newPlayer);
		model.addAttribute("match", matchDto);
		if (AJAX_HEADER_VALUE.equals(request.getHeader(AJAX_HEADER_NAME))) {
			// It is an Ajax request, render only #items fragment of the page.
			return "createMatchForm:: #paticipants";
		} else {
			// It is a standard HTTP request, render whole page.
			return "createMatchForm";
		}
	}
	
}
