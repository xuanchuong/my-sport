package domain.repository;

import domain.entity.Role;

public interface RoleRepository {

	Role findByName(String name);

	Role saveRole(Role role);

}
