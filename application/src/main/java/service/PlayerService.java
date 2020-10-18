package service;

import domain.entity.PasswordResetToken;
import domain.entity.Player;
import domain.entity.Role;
import domain.repository.EmailRepository;
import domain.repository.PasswordTokenRepository;
import domain.repository.PlayerRepository;
import domain.repository.RoleRepository;
import domain.vo.CreateUserCommand;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;


@AllArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final EmailRepository emailRepository;
    private final PasswordTokenRepository passwordTokenRepository;

    @Transactional
    public Player getPlayerByEmail(String email) {
        return playerRepository.findUserByEmail(email);
    }

    @Transactional
    public Optional<Player> getPlayerById(long id) {
        return playerRepository.findById(id);
    }

    @Transactional
    public Player add(CreateUserCommand createUserCommand) {
        if (!createUserCommand.isValid()) {
            throw new IllegalArgumentException("createUserCommand's fields are missing");
        }
        Role role = roleRepository.findByName("PLAYER");
        if (role == null) {
            role = roleRepository.saveRole(Role.builder().name("PLAYER").build());
        }
        Player player = Player.builder()
                .email(createUserCommand.getEmail())
                .firstName(createUserCommand.getFirstName())
                .lastName(createUserCommand.getLastName())
                .password(passwordEncoder.encode(createUserCommand.getPassword()))
                .roles(Collections.singletonList(role))
                .build();
        return playerRepository.save(player);
    }

    @Transactional
    public void deletePlayer(String email) {
        playerRepository.deleteByEmail(email);
    }


    public Player getSessionPlayer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return playerRepository.findUserByEmail(email);
    }

    public void sendEmailResetPass(Player player, String token) {
        emailRepository.sendResetPasswordMessage(player.getEmail(), token);
    }

    public void createPasswordResetTokenForUser(Player player, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, player);
        passwordTokenRepository.save(passwordResetToken);

    }
}
