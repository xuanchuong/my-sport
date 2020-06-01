package my.sport.data.jpa.repository;

import my.sport.data.jpa.entity.JpaRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<JpaRole, Long> {

	JpaRole findByName(String name);

	void delete(JpaRole role);

}
