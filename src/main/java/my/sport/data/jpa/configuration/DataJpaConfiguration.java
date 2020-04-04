package my.sport.data.jpa.configuration;

import my.sport.data.jpa.repository.PlayerJpaRepository;
import my.sport.data.jpa.repository.PlayerRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataJpaConfiguration {

    @Bean
    public PlayerRepositoryAdapter playerRepositoryAdapter(PlayerJpaRepository playerJpaRepository) {
        return new PlayerRepositoryAdapter(playerJpaRepository);
    }
}
