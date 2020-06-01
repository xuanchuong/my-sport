package my.sport.data.jpa.configuration;

import my.sport.data.jpa.mapper.FootballMatchMapper;
import my.sport.data.jpa.mapper.PlayerMapper;
import my.sport.data.jpa.mapper.RoleMapper;
import my.sport.data.jpa.repository.FootballMatchJpaRepository;
import my.sport.data.jpa.repository.FootballMatchRepositoryAdapter;
import my.sport.data.jpa.repository.PlayerJpaRepository;
import my.sport.data.jpa.repository.PlayerRepositoryAdapter;
import my.sport.data.jpa.repository.RoleJpaRepository;
import my.sport.data.jpa.repository.RoleRepositoryAdapter;
import my.sport.domain.repository.FootballMatchRepository;
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
    public PlayerMapper playerMapper() {
        return new PlayerMapper();
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
}
