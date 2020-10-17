package repository;

import domain.entity.Player;
import domain.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import entity.JpaPlayer;
import mapper.PlayerMapper;
import java.util.Optional;

@AllArgsConstructor
public class PlayerRepositoryAdapter implements PlayerRepository {

    private final PlayerJpaRepository playerJpaRepository;
    private final PlayerMapper playerMapper;

    @Override
    public Player findUserByEmail(String email) {
        JpaPlayer jpaPlayer = playerJpaRepository.findUserByEmail(email);
        return jpaPlayer == null ? null : playerMapper.map(jpaPlayer);
    }

    @Override
    public Optional<Player> findById(Long id) {
        return playerJpaRepository.findById(id).map(playerMapper::map);
    }

    @Override
    public Player save(Player player) {
        return playerMapper.map(playerJpaRepository.save(playerMapper.map(player)));
    }

    @Override
    public void deleteByEmail(String email) {
        playerJpaRepository.deleteByEmail(email);
    }

    @Override
    public void deleteAll() {
        playerJpaRepository.deleteAll();
    }
}
