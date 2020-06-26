package my.sport.bootstrap;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import my.sport.MySportSpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MySportSpringApplication.class)
@PactFolder("pact")
@ActiveProfiles("pact")
@Provider("match-api")
public class MatchProviderPactIT {

}
