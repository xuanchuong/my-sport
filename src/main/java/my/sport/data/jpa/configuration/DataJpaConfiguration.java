package my.sport.data.jpa.configuration;

import my.sport.data.jpa.adapter.PasswordTokenRepositoryAdapter;
import my.sport.data.jpa.mapper.*;
import my.sport.data.jpa.repository.*;
import my.sport.domain.repository.FootballMatchRepository;
import my.sport.domain.repository.PasswordTokenRepository;
import my.sport.domain.repository.RoleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
    FootballMatchMapper jpaFootballMatchMapper(PlayerMapper playerMapper) {
        return new FootballMatchMapper(playerMapper);
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
