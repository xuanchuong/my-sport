package my.sport.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configurable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static String USER_NAME = "user";
	private static String USER_ROLE = "USER";
	private static String PASS = "1234";

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(USER_NAME).password(passwordEncoder().encode(PASS)).roles(USER_ROLE);
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers("/player/detail/**").authenticated().and().formLogin()
			.loginPage("/login").permitAll()
			.and()
			.logout().permitAll();
	}
}
