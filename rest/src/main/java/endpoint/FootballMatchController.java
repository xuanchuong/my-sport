package endpoint;

import domain.entity.FootballMatch;
import domain.entity.Player;
import domain.vo.CreateFootballMatchCommand;
import dto.FootballMatchOut;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import mapper.FootballMatchMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.FootballMatchService;
import service.PlayerService;

@Controller
@RequestMapping({"/rest/api/v1/match"})
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FootballMatchController {

    FootballMatchService matchService;
    PlayerService playerService;
    FootballMatchMapper footballMatchMapper;

    @GetMapping("/all")
    public ResponseEntity<FootballMatchOut[]> getAllMatch() {
        FootballMatchOut[] responseBody = footballMatchMapper.map(matchService.getAllMatch());
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FootballMatchOut> getMatch(@PathVariable String id) {
        FootballMatch footballMatch = matchService.getMatchById(Long.valueOf(id));
        if (footballMatch == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        FootballMatchOut footballMatchOut = footballMatchMapper.map(footballMatch);
        return ResponseEntity.status(HttpStatus.OK).body(footballMatchOut);
    }

    @PostMapping("/create")
    public ResponseEntity<FootballMatchOut> create(@RequestBody CreateFootballMatchCommand createFootballMatchCommand) {
        FootballMatch footballMatch = matchService.createNewMatch(createFootballMatchCommand);
        FootballMatchOut footballMatchOut = footballMatchMapper.map(footballMatch);
        return ResponseEntity.status(HttpStatus.CREATED).body(footballMatchOut);
    }

    @PutMapping("/join")
    public ResponseEntity<FootballMatchOut> joinTheMatch(@RequestParam String matchId) {
        FootballMatch footballMatch = matchService
                .getMatchById(Long.valueOf(matchId));
        if (footballMatch == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Player currentPlayer = playerService.getSessionPlayer();
        if(matchService.hasUserJoinedTheMatch(footballMatch, currentPlayer)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        footballMatch.getParticipants().add(currentPlayer);
        matchService.updateMatch(footballMatch);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(footballMatchMapper.map(footballMatch));
    }

    @PutMapping("/leave")
    public ResponseEntity<FootballMatchOut> leaveTheMatch(@RequestParam String matchId) {
        FootballMatch footballMatch = matchService.getMatchById(Long.valueOf(matchId));
        if (footballMatch == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Player currentPlayer = playerService.getSessionPlayer();
        if(!matchService.hasUserJoinedTheMatch(footballMatch, currentPlayer)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        matchService.leaveTheMatch(footballMatch, currentPlayer);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(footballMatchMapper.map(footballMatch));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteMatch(@RequestParam String id) {
        matchService.deleteMatch(Long.valueOf(id));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Boolean.TRUE);
    }

    @PutMapping("/cancel")
    public ResponseEntity<HttpStatus> cancelTheMatch(@RequestParam String matchId) {
        FootballMatch footballMatch = matchService.getMatchById(Long.valueOf(matchId));
        Player currentPlayer = playerService.getSessionPlayer();
        matchService.cancel(footballMatch, currentPlayer);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();

    }

}
