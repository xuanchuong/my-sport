package my.sport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(
		MySportSpringApplication.class
)
public class MySportSpringApplicationLocal{

	public static void main(String[] args) {
		SpringApplication.run(MySportSpringApplicationLocal.class, args);
	}
}
