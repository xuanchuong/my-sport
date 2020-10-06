package my.sport.controller;

import lombok.AllArgsConstructor;
import my.sport.application.service.FootballMatchService;
import my.sport.controller.mapper.MatchMapper;
import my.sport.domain.entity.FootballMatch;
import my.sport.rest.dto.CreateFootballMatchCommandDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/match")
@AllArgsConstructor
public class MatchController {

	private final FootballMatchService matchService;
	private final MatchMapper matchMapper;
	private static final String MATCH_ATTR = "match";

	@GetMapping("/create")
	public String createMatch(Model model) {
		CreateFootballMatchCommandDTO footballMatchCommandDTO = new CreateFootballMatchCommandDTO();
		model.addAttribute(MATCH_ATTR, footballMatchCommandDTO);
		return"createMatchForm";
	}
	
	@GetMapping("/detail")
	public String getMatchDetail(@RequestParam String id, Model model) {
		FootballMatch footballMatch = matchService.getMatchById(Long.valueOf(id));
		model.addAttribute(MATCH_ATTR, footballMatch);
		return "matchDetail";
	}
	
	@PostMapping("/create")
	public ModelAndView creatingMatch(@Valid CreateFootballMatchCommandDTO footballMatchDTO, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("createMatchForm", MATCH_ATTR, footballMatchDTO);
		}
		matchService.createNewMatch(matchMapper.map(footballMatchDTO));
		return new ModelAndView("redirect:/dashboard");
	}
}
