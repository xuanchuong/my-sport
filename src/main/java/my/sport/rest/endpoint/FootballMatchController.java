package my.sport.rest.endpoint;

import lombok.AllArgsConstructor;
import my.sport.application.service.FootballMatchService;
import my.sport.application.service.PlayerService;
import my.sport.domain.entity.FootballMatch;
import my.sport.domain.entity.Player;
import my.sport.rest.dto.CreateFootballMatchCommandDTO;
import my.sport.rest.dto.FootballMatchDto;
import my.sport.rest.mapper.FootballMatchMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/rest/api/v1/match", "/match"})
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class FootballMatchController {

    private static final String DASH_BOARD = "dashboard";
    private static final String MATCH = "match";
    private FootballMatchService matchService;
    private PlayerService playerService;
    private FootballMatchMapper footballMatchMapper;

    @GetMapping("/all")
    public ResponseEntity<FootballMatchDto[]> getAllMatch() {
        my.sport.rest.dto.FootballMatchDto[] allAvailableFootballMatch = matchService.getAllMatch()
                .stream().map(footballMatchMapper::map)
                .toArray(FootballMatchDto[]::new);
        return ResponseEntity.status(HttpStatus.OK).body(allAvailableFootballMatch);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FootballMatchDto> getMatch(@PathVariable String id) {
        FootballMatch footballMatch = matchService
                .getMatchById(Long.valueOf(id));
        if (footballMatch == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        FootballMatchDto footballMatchDto = footballMatchMapper.map(footballMatch);
        return ResponseEntity.status(HttpStatus.OK).body(footballMatchDto);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody CreateFootballMatchCommandDTO createFootballMatchCommandDTO) {
        FootballMatch footballMatch = matchService.createNewMatch(createFootballMatchCommandDTO);
        FootballMatchDto footballMatchDto = footballMatchMapper.map(footballMatch);
        return ResponseEntity.status(HttpStatus.CREATED).body(footballMatchDto);
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

}
