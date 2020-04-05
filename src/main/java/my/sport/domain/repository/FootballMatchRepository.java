package my.sport.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.sport.domain.entity.FootballMatch;

@Repository
public interface FootballMatchRepository extends JpaRepository<FootballMatch, Long>{

}
