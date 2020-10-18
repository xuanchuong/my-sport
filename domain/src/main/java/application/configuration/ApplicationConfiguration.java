package application.configuration;

import application.service.FootballMatchService;
import application.service.PlayerService;
import domain.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfiguration {


    @Bean
    public FootballMatchService footballMatchService(FootballMatchRepository footballMatchRepository,
                                                     PlayerService playerService) {
        return new FootballMatchService(footballMatchRepository, playerService);
    }

    @Bean
    public PlayerService playerService(PlayerRepository playerRepository, PasswordEncoder passwordEncoder,
                                       RoleRepository roleRepository, EmailRepository emailRepository,
                                       PasswordTokenRepository passwordTokenRepository) {
        return new PlayerService(playerRepository,passwordEncoder, roleRepository, emailRepository,
                passwordTokenRepository);
    }

}
