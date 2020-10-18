package repository;

import entity.FootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootballMatchJpaRepository extends JpaRepository<FootballMatch, Long>{

}
