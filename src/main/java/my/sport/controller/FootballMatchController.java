package my.sport.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import my.sport.dto.FootballMatchDto;
import my.sport.model.FootballMatch;
import my.sport.model.Player;
import my.sport.service.FootballMatchService;
import my.sport.service.PlayerService;

@Controller
@RequestMapping("/rest/api/v1/match")
public class FootballMatchController {

	@Autowired
	private FootballMatchService matchService;
	@Autowired
	private PlayerService playerService;
	private static final String AJAX_HEADER_NAME = "X-Requested-With";
	private static final String AJAX_HEADER_VALUE = "XMLHttpRequest";

	@GetMapping
	public String createMatch(Model model) {
		FootballMatchDto footballMatchDto = new FootballMatchDto();
		footballMatchDto.setStartDate(Calendar.getInstance().getTime());
		Player firstPlayer = new Player();
		Player secondPlayer = new Player();
		firstPlayer.setFirstName("chuong");
		secondPlayer.setFirstName("Huy");
		List<Player> paticipants = Arrays.asList(firstPlayer, secondPlayer);
		footballMatchDto.setPaticipants(paticipants);
		model.addAttribute("match", footballMatchDto);
		return "createMatchForm";
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity getMatch(@PathVariable String id) {
		FootballMatch footballMatch = matchService
				.getMatchById(Long.valueOf(id));
		if (footballMatch == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(footballMatch);
	}

	@GetMapping("/detail")
	public String getMatchDetail(@RequestParam String id, Model model) {
		FootballMatch footballMatch = matchService
				.getMatchById(Long.valueOf(id));
		Player currentPlayer = playerService.getSessionPlayer();
		boolean isMatchOwner = currentPlayer.getId().equals(footballMatch.getOwner().getId());
		boolean isJoined = footballMatch.getPaticipants().contains(currentPlayer);
		model.addAttribute("match", footballMatch);
		model.addAttribute("isMatchOwner", isMatchOwner);
		model.addAttribute("isJoined", isJoined);
		return "matchDetail";
	}

	@PostMapping("/join")
	public String joinTheMatch(@RequestParam String id) {
		FootballMatch footballMatch = matchService
				.getMatchById(Long.valueOf(id));
		Player currentPlayer = playerService.getSessionPlayer();
		footballMatch.getPaticipants().add(currentPlayer);
		matchService.updateMatch(footballMatch);
		return "dashboard";
	}

	@PostMapping("/delete")
	public ModelAndView deleteMatch(@RequestParam String id, Model model) {
			matchService.deleteMatch(Long.valueOf(id));
		return new ModelAndView("dashboard");
	}

	@PostMapping
	public ModelAndView creatingMatch(
			@ModelAttribute("match") @Valid FootballMatchDto matchDto,
			BindingResult resutl, Errors errors) {
		if (resutl.hasErrors()) {
			return new ModelAndView("createMatchForm", "match", matchDto);
		}
		matchService.createNewMatch(matchDto);
		return new ModelAndView("dashboard");
	}

	@PostMapping("/addItem")
	public String addOrder(@ModelAttribute("match") FootballMatchDto matchDto, Model model) {
		Player newPlayer = new Player();
		newPlayer.setFirstName("random name");
		matchDto.getPaticipants().add(newPlayer);
		model.addAttribute("match", matchDto);
		HttpServletRequest request = ServletActionContext.getRequest();
		if (AJAX_HEADER_VALUE.equals(request.getHeader(AJAX_HEADER_NAME))) {
			// It is an Ajax request, render only #items fragment of the page.
			return "createMatchForm:: #paticipants";
		} else {
			// It is a standard HTTP request, render whole page.
			return "createMatchForm";
		}
	}
	
}
