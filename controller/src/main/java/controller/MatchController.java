package controller;

import application.service.FootballMatchService;
import application.service.PlayerService;
import domain.entity.FootballMatch;
import domain.entity.Player;
import dto.CreateFootballMatchCommandDTO;
import dto.FootballMatchDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mapper.MatchMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/match")
@AllArgsConstructor
@Slf4j
public class MatchController {

	public static final String MATCH_DETAIL = "matchDetail";
	public static final String IS_JOINED = "isJoined";
	private final FootballMatchService matchService;
	private final PlayerService playerService;
	private final MatchMapper matchMapper;
	private static final String MATCH_ATTR = "match";

	@GetMapping("/create")
	public String createMatch(Model model) {
		CreateFootballMatchCommandDTO footballMatchCommandDTO = new CreateFootballMatchCommandDTO();
		model.addAttribute(MATCH_ATTR, footballMatchCommandDTO);
		return "createMatchForm";
	}

	@GetMapping("/detail")
	public String getMatchDetail(@RequestParam String id, Model model) {
		FootballMatch footballMatch = matchService.getMatchById(Long.valueOf(id));
		Player sessionUser = playerService.getSessionPlayer();
		boolean isJoined = sessionUser != null && matchService.hasUserJoinedTheMatch(footballMatch, sessionUser);
		model.addAttribute(IS_JOINED, isJoined);
		model.addAttribute(MATCH_ATTR, footballMatch);
		return MATCH_DETAIL;
	}

	@PostMapping("/create")
	public String creatingMatch(@Valid @ModelAttribute(MATCH_ATTR) CreateFootballMatchCommandDTO footballMatchDTO,
								BindingResult result) {
		if (result.hasErrors()) {
			return "createMatchForm";
		}

		matchService.createNewMatch(matchMapper.map(footballMatchDTO));
		return "redirect:/dashboard";
	}

	@PostMapping("/join")
	public String joinTheMatch(@ModelAttribute(MATCH_ATTR) FootballMatchDTO footballMatchDTO, Model model) {
		Player sessionUser = playerService.getSessionPlayer();
		FootballMatch footballMatch = matchService.getMatchById(footballMatchDTO.getId());
		try {
			matchService.joinTheMatch(footballMatch, sessionUser);
			model.addAttribute(IS_JOINED, true);
		} catch (IllegalArgumentException ex) {
			log.warn(ex.getMessage());
		}
		model.addAttribute(MATCH_ATTR, footballMatch);
		return MATCH_DETAIL;
	}

	@PostMapping("/leave")
	public String leaveTheMatch(@ModelAttribute(MATCH_ATTR) FootballMatchDTO footballMatchDTO, Model model) {
		Player sessionUser = playerService.getSessionPlayer();
		FootballMatch footballMatch = matchService.getMatchById(footballMatchDTO.getId());
		try {
			matchService.leaveTheMatch(footballMatch, sessionUser);
			model.addAttribute(IS_JOINED, false);
		} catch (IllegalArgumentException ex) {
			log.warn(ex.getMessage());
		}
		model.addAttribute(MATCH_ATTR, footballMatch);
		return MATCH_DETAIL;
	}
}
