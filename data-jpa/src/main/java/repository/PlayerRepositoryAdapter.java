package repository;

import domain.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import entity.Player;
import mapper.PlayerMapper;
import java.util.Optional;

@AllArgsConstructor
public class PlayerRepositoryAdapter implements PlayerRepository {

    private final PlayerJpaRepository playerJpaRepository;
    private final PlayerMapper playerMapper;

    @Override
    public domain.entity.Player findUserByEmail(String email) {
        Player player = playerJpaRepository.findUserByEmail(email);
        return player == null ? null : playerMapper.map(player);
    }

    @Override
    public Optional<domain.entity.Player> findById(Long id) {
        return playerJpaRepository.findById(id).map(playerMapper::map);
    }

    @Override
    public domain.entity.Player save(domain.entity.Player player) {
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
