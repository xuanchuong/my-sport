package repository;

import entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

	void delete(Role role);

}
