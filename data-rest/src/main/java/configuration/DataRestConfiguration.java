package configuration;

import adapter.EmailRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class DataRestConfiguration {

    @Bean
    public EmailRepositoryAdapter emailRepositoryAdapter(JavaMailSender emailSender) {
        return new EmailRepositoryAdapter(emailSender);
    }
}
