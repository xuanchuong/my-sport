package configuration;

import adapter.FootballMatchRepositoryAdapter;
import adapter.PasswordTokenRepositoryAdapter;
import adapter.PlayerRepositoryAdapter;
import adapter.RoleRepositoryAdapter;
import domain.repository.FootballMatchRepository;
import domain.repository.PasswordTokenRepository;
import domain.repository.RoleRepository;
import mapper.FootballMatchMapper;
import mapper.PasswordTokenMapper;
import mapper.PlayerMapper;
import mapper.RoleMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.*;

@Configuration
@EnableJpaAuditing
@ComponentScan({"mapper", "adapter"})
@EnableJpaRepositories("repository")
@EntityScan("entity")
public class DataJpaConfiguration {

    @Bean
    public PlayerRepositoryAdapter playerRepositoryAdapter(PlayerJpaRepository playerJpaRepository, PlayerMapper playerMapper) {
        return new PlayerRepositoryAdapter(playerJpaRepository, playerMapper);
    }

    @Bean
    public RoleRepository roleRepository(RoleJpaRepository roleJpaRepository, RoleMapper jpaRoleMapper) {
        return new RoleRepositoryAdapter(roleJpaRepository, jpaRoleMapper);
    }

    @Bean
    RoleMapper jpaRoleMapper() {
        return new RoleMapper();
    }

    @Bean
    FootballMatchRepository footballMatchRepository(FootballMatchJpaRepository footballMatchJpaRepository,
                                                    FootballMatchMapper footballMatchMapper) {
        return new FootballMatchRepositoryAdapter(footballMatchJpaRepository, footballMatchMapper);
    }

    @Bean
    PasswordTokenRepository passwordTokenRepository(PasswordTokenJpaRepository tokenJpaRepository,
                                                    PasswordTokenMapper mapper) {
        return new PasswordTokenRepositoryAdapter(tokenJpaRepository, mapper);
    }
}
