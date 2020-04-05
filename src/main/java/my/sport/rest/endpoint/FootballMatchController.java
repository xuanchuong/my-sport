package my.sport.rest.endpoint;

import lombok.AllArgsConstructor;
import my.sport.application.service.FootballMatchService;
import my.sport.application.service.PlayerService;
import my.sport.domain.entity.FootballMatch;
import my.sport.domain.entity.Player;
import my.sport.dto.FootballMatchDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;

@Controller
@RequestMapping({"/rest/api/v1/match", "/match"})
@AllArgsConstructor
public class FootballMatchController {

    private static final String DASH_BOARD = "dashboard";
    private static final String MATCH = "match";
    private FootballMatchService matchService;
    private PlayerService playerService;

    @GetMapping
    public String createMatch(Model model) {
        FootballMatchDto footballMatchDto = new FootballMatchDto();
        footballMatchDto.setStartDate(Calendar.getInstance().getTime());
        Player firstPlayer = new Player();
        Player secondPlayer = new Player();
        firstPlayer.setFirstName("chuong");
        secondPlayer.setFirstName("Huy");
        footballMatchDto.setPaticipants(new ArrayList<>());
        model.addAttribute(MATCH, footballMatchDto);
        return "createMatchForm";
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FootballMatch> getMatch(@PathVariable String id) {
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
        model.addAttribute(MATCH, footballMatch);
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
        return DASH_BOARD;
    }

    @PostMapping("/delete")
    public ModelAndView deleteMatch(@RequestParam String id) {
        matchService.deleteMatch(Long.valueOf(id));
        return new ModelAndView(DASH_BOARD);
    }

    @PostMapping
    public ModelAndView creatingMatch(
            @ModelAttribute(MATCH) @Valid FootballMatchDto matchDto,
            BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("createMatchForm", MATCH, matchDto);
        }
        matchService.createNewMatch(matchDto);
        return new ModelAndView(DASH_BOARD);
    }
}
