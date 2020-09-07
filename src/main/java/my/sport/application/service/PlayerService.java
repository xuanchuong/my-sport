package my.sport.application.service;

import lombok.AllArgsConstructor;
import my.sport.domain.entity.PasswordResetToken;
import my.sport.domain.entity.Player;
import my.sport.domain.repository.*;
import my.sport.rest.dto.CreateUserCommandDTO;
import my.sport.rest.dto.UpdateUserCommandDTO;
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
    public Player add(CreateUserCommandDTO createUserCommandDTO) {
        Player player = Player.builder()
                .email(createUserCommandDTO.getEmail())
                .firstName(createUserCommandDTO.getFirstName())
                .lastName(createUserCommandDTO.getLastName())
                .password(passwordEncoder.encode(createUserCommandDTO.getPassword()))
                .roles(Collections.singletonList(roleRepository.findByName("PLAYER")))
                .phoneNumber(createUserCommandDTO.getPhoneNumber())
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

    @Transactional
    public Player updatePlayer(UpdateUserCommandDTO updateUserCommandDTO) {
        Player currentUser = getPlayerByEmail(getSessionPlayer().getEmail());
        currentUser.toBuilder()
                .firstName(updateUserCommandDTO.getFirstName())
                .lastName(updateUserCommandDTO.getLastName())
                .phoneNumber(updateUserCommandDTO.getPhoneNumber())
                .build();
        return playerRepository.save(currentUser);

    }

    public void sendEmailResetPass(Player player, String token) {
        emailRepository.sendResetPasswordMessage(player.getEmail(), token);
    }

    public void createPasswordResetTokenForUser(Player player, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, player);
        passwordTokenRepository.save(passwordResetToken);

    }
}
