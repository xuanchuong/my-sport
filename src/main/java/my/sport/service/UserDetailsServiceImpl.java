package my.sport.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import my.sport.model.Player;
import my.sport.model.Role;
import my.sport.repository.PlayerRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private PlayerRepository<Player> playerRepository;

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		Player player = playerRepository.findUserByEmail(email);
		if (player == null) {
			throw new UsernameNotFoundException(
					"No user found with username: " + email);
		}
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		return new org.springframework.security.core.userdetails.User(
				player.getEmail(), player.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
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
