package my.sport.application.config;

import my.sport.application.service.FootballMatchService;
import my.sport.application.service.PlayerService;
import my.sport.application.service.UserDetailsServiceImpl;
import my.sport.domain.repository.FootballMatchRepository;
import my.sport.domain.repository.PlayerRepository;
import my.sport.domain.repository.RoleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {
    @Bean
    public FootballMatchService footballMatchService(FootballMatchRepository footballMatchRepository,
                                                     PlayerService playerService) {
        return new FootballMatchService(footballMatchRepository, playerService);
    }

    @Bean
    public PlayerService playerService(PlayerRepository playerRepository, PasswordEncoder passwordEncoder,
                                       RoleRepository roleRepository) {
        return new PlayerService(playerRepository,passwordEncoder, roleRepository);
    }

    @Bean
    public UserDetailsService userDetailsService(PlayerRepository playerRepository) {
        return new UserDetailsServiceImpl(playerRepository);
    }
}
