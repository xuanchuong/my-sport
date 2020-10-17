package repository;

import domain.entity.FootballMatch;
import domain.repository.FootballMatchRepository;
import lombok.AllArgsConstructor;
import entity.JpaFootballMatch;
import mapper.FootballMatchMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FootballMatchRepositoryAdapter implements FootballMatchRepository {

    private final FootballMatchJpaRepository footballMatchJpaRepository;
    private final FootballMatchMapper footballMatchMapper;

    @Override
    public FootballMatch save(FootballMatch footballMatch) {
        JpaFootballMatch jpaFootballMatch = footballMatchMapper.map(footballMatch);
        return footballMatchMapper.map(footballMatchJpaRepository.save(jpaFootballMatch));
    }

    @Override
    public void deleteById(Long id) {
        footballMatchJpaRepository.deleteById(id);
    }

    @Override
    public Optional<FootballMatch> findById(Long id) {
        return footballMatchJpaRepository.findById(id).map(footballMatchMapper::map);
    }

    @Override
    public List<FootballMatch> findAll() {
        return footballMatchJpaRepository.findAll().stream().map(footballMatchMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        footballMatchJpaRepository.deleteAll();
    }
}
