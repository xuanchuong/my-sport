package my.sport.rest.config;

import my.sport.rest.mapper.FootballMatchMapper;
import my.sport.rest.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    FootballMatchMapper footballMatchMapper() {
        return new FootballMatchMapper();
    }
}
