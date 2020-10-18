package application;

import application.configuration.ApplicationConfiguration;
import config.ServerSecurityConfig;
import configuration.ControllerConfiguration;
import configuration.DataJpaConfiguration;
import configuration.DataRestConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        DataJpaConfiguration.class,
        ApplicationConfiguration.class,
        DataRestConfiguration.class,
        ControllerConfiguration.class,
        ServerSecurityConfig.class
})
public class MySportSpringApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MySportSpringApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MySportSpringApplication.class, args);
    }
}
