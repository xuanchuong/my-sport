package repository;

import entity.JpaFootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootballMatchJpaRepository extends JpaRepository<JpaFootballMatch, Long>{

}
