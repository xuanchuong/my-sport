package my.sport.data.jpa.repository;

import lombok.AllArgsConstructor;
import my.sport.domain.entity.Player;
import my.sport.domain.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class PlayerRepositoryAdapter implements PlayerRepository {

    private PlayerJpaRepository playerJpaRepository;

    @Override
    public List<Player> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public Player findUserByEmail(String email) {
        return playerJpaRepository.findUserByEmail(email);
    }

    @Override
    public List<Player> findAll() {
        return playerJpaRepository.findAll();
    }

    @Override
    public Optional<Player> findById(Long id) {
        return playerJpaRepository.findById(id);
    }

    @Override
    public Object save(Player player) {
        return playerJpaRepository.save(player);
    }

    @Override
    public void deleteById(Long id) {
        playerJpaRepository.deleteById(id);
    }
}
