package my.sport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@Import(
		MySportSpringApplication.class
)
public class MySportSpringApplicationLocal{

	public static void main(String[] args) {

		SpringApplication springApp = new SpringApplication(MySportSpringApplicationLocal.class);
		springApp.setAdditionalProfiles("h2-to-local");
		springApp.run(args);
	}
}
