package my.sport.bootstrap;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import my.sport.MySportSpringApplication;
import my.sport.domain.entity.FootballMatch;
import my.sport.domain.entity.Player;
import my.sport.domain.entity.Role;
import my.sport.domain.repository.FootballMatchRepository;
import my.sport.domain.repository.PlayerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MySportSpringApplication.class)
@PactFolder("pact")
@ActiveProfiles("pact")
@Provider("match-api")
public class MatchProviderPactIT {

    @LocalServerPort
    private int serverPort;

    @Autowired
    private FootballMatchRepository footballMatchRepository;

    @Autowired
    private PlayerRepository playerRepository;

    Player createPlayer() {
        Player player = new Player();
        player.setEmail("email@gmail.com");
        player.setFirstName("firstName");
        player.setLastName("lastName");
        player.setPassword("password");
        player.setDescription("description");
        player.setRoles(Collections.singletonList(new Role()));
        return player;
    }

    @BeforeEach
    void setUp(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", serverPort));
        footballMatchRepository.deleteAll();
        playerRepository.deleteAll();
    }

    @AfterEach
    void cleanUp() {
        footballMatchRepository.deleteAll();
        playerRepository.deleteAll();
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void testTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("exist football have id = 123")
    public void existFootball() {
        footballMatchRepository.save(createFootballMatch());
    }

    private FootballMatch createFootballMatch() {
        Player owner = createPlayer();
        owner = playerRepository.save(owner);
        return FootballMatch.builder().title("title").description("description")
                .location("location").numberOfPlayers(10).
                        owner(owner).participants(Collections.emptyList())
                        .build();
    }

    @State("user request to join an existing match have id = 123")
    public void joinAnExistingMatch() {
        footballMatchRepository.save(createFootballMatch());
    }
}
