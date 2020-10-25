package controller;

import domain.entity.FootballMatch;
import domain.entity.Player;
import dto.CreateFootballMatchCommandDTO;
import dto.FootballMatchDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mapper.MatchMapper;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.FootballMatchService;
import service.PlayerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/match")
@AllArgsConstructor
@Slf4j
public class MatchController {

	private static final String MATCH_DETAIL = "matchDetail";
	private static final String IS_JOINED = "isJoined";
	private static final String IS_PENDING_JOIN_REQUEST = "isPendingJoinRequest";
	private static final String JOINABLE = "joinable";
	private static final String IS_MATCH_OWNER = "isMatchOwner";

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
		boolean isJoined = matchService.hasUserJoinedTheMatch(footballMatch, sessionUser);
		boolean isPendingRequest = matchService.hasPendingRequest(footballMatch, sessionUser);
		model.addAttribute(IS_JOINED, isJoined);
		model.addAttribute(IS_PENDING_JOIN_REQUEST, isPendingRequest);
		model.addAttribute(JOINABLE, BooleanUtils.isFalse(isJoined || isPendingRequest));
		model.addAttribute(MATCH_ATTR, footballMatch);
		model.addAttribute(IS_MATCH_OWNER, footballMatch.getOwner().equals(sessionUser));
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
			model.addAttribute(IS_PENDING_JOIN_REQUEST, true);
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

	@PostMapping("/cancel/request")
	public String cancelJoinRequest(@ModelAttribute(MATCH_ATTR) FootballMatchDTO footballMatchDTO, Model model) {
		Player sessionUser = playerService.getSessionPlayer();
		FootballMatch footballMatch = matchService.getMatchById(footballMatchDTO.getId());
		try {
			matchService.cancelJoinRequest(footballMatch, sessionUser);
		} catch (IllegalArgumentException ex) {
			log.warn(ex.getMessage());
		}
		model.addAttribute(MATCH_ATTR, footballMatch);
		return MATCH_DETAIL;
	}

	@PostMapping("/participant/accept")
	public String acceptParticipant(@RequestParam String email, @ModelAttribute(MATCH_ATTR) FootballMatchDTO footballMatchDTO,
									Model model) {
		Player sessionUser = playerService.getSessionPlayer();
		Player requestPlayer = playerService.getPlayerByEmail(email);
		FootballMatch footballMatch = matchService.getMatchById(footballMatchDTO.getId());
		footballMatch = matchService.acceptJoinRequest(footballMatch, sessionUser, requestPlayer);
		model.addAttribute(MATCH_ATTR, footballMatch);
		return MATCH_DETAIL;
	}

	@PostMapping("/participant/reject")
	public String rejectParticipant(@RequestParam String email, @ModelAttribute(MATCH_ATTR) FootballMatchDTO footballMatchDTO,
									Model model) {
		Player sessionUser = playerService.getSessionPlayer();
		Player requestPlayer = playerService.getPlayerByEmail(email);
		FootballMatch footballMatch = matchService.getMatchById(footballMatchDTO.getId());
		footballMatch = matchService.rejectJoinRequest(footballMatch, sessionUser, requestPlayer);
		model.addAttribute(MATCH_ATTR, footballMatch);
		return MATCH_DETAIL;
	}
}
