package application.service;

import domain.entity.Player;
import domain.entity.Role;
import domain.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
	
	PlayerRepository playerRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		Player player = playerRepository.findUserByEmail(email);
		if (player == null) {
			throw new UsernameNotFoundException(
					"No user found with username: " + email);
		}
		return new org.springframework.security.core.userdetails.User(
				player.getEmail(), player.getPassword(), true,
				true, true, true,
				getAuthorities(player.getRoles()));
	}

	private static List<GrantedAuthority> getAuthorities(List<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	
}
