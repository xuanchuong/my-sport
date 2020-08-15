package my.sport.rest.endpoint;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/rest/api/v1/match"})
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FootballMatchController {

    static final String DASH_BOARD = "dashboard";
    static final String MATCH = "match";
    FootballMatchService matchService;
    PlayerService playerService;
    FootballMatchMapper footballMatchMapper;

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
    public ResponseEntity<FootballMatchDto> create(@RequestBody CreateFootballMatchCommandDTO createFootballMatchCommandDTO) {
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

    @PutMapping("/join")
    public ResponseEntity<HttpStatus> joinTheMatch(@RequestParam String matchId) {
        FootballMatch footballMatch = matchService
                .getMatchById(Long.valueOf(matchId));
        Player currentPlayer = playerService.getSessionPlayer();
        if (footballMatch.getOwner().getEmail().equals(currentPlayer.getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        footballMatch.getPaticipants().add(currentPlayer);
        matchService.updateMatch(footballMatch);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteMatch(@RequestParam String id) {
        matchService.deleteMatch(Long.valueOf(id));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Boolean.TRUE);
    }

}
