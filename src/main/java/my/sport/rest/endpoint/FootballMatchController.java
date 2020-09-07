package my.sport.rest.endpoint;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import my.sport.application.service.FootballMatchService;
import my.sport.application.service.PlayerService;
import my.sport.domain.entity.FootballMatch;
import my.sport.domain.entity.Player;
import my.sport.rest.dto.CreateFootballMatchCommandDTO;
import my.sport.rest.dto.FootballMatchOut;
import my.sport.rest.mapper.FootballMatchMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        FootballMatchOut[] allAvailableFootballMatch = matchService.getAllMatch()
                .stream().map(footballMatchMapper::map)
                .toArray(FootballMatchOut[]::new);
        return ResponseEntity.status(HttpStatus.OK).body(allAvailableFootballMatch);
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
    public ResponseEntity<FootballMatchOut> create(@RequestBody CreateFootballMatchCommandDTO createFootballMatchCommandDTO) {
        FootballMatch footballMatch = matchService.createNewMatch(createFootballMatchCommandDTO);
        FootballMatchOut footballMatchOut = footballMatchMapper.map(footballMatch);
        return ResponseEntity.status(HttpStatus.CREATED).body(footballMatchOut);
    }

    @PutMapping("/join")
    public ResponseEntity<HttpStatus> joinTheMatch(@RequestParam String matchId) {
        FootballMatch footballMatch = matchService
                .getMatchById(Long.valueOf(matchId));
        Player currentPlayer = playerService.getSessionPlayer();
        if (footballMatch.getOwner().getEmail().equals(currentPlayer.getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        footballMatch.getParticipants().add(currentPlayer);
        matchService.updateMatch(footballMatch);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteMatch(@RequestParam String id) {
        matchService.deleteMatch(Long.valueOf(id));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Boolean.TRUE);
    }

}
