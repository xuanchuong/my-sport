package jlpt.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan({"jlpt.service", "jlpt.repository", "jlpt.config", "jlpt.controller"})
@EnableTransactionManagement
@EnableJpaRepositories("jlpt.repository")
@EnableAutoConfiguration
public class NihongoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(NihongoSpringApplication.class, args);
	}

}
