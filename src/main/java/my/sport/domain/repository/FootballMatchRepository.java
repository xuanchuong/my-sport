package my.sport.domain.repository;

import my.sport.domain.entity.FootballMatch;

import java.util.List;
import java.util.Optional;

public interface FootballMatchRepository{

    FootballMatch save(FootballMatch footballMatch);

    void deleteById(Long id);

    Optional<FootballMatch> findById(Long id);

    List<FootballMatch> findAll();
}
