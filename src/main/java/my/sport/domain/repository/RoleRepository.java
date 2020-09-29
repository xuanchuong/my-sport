package my.sport.domain.repository;

import my.sport.domain.entity.Role;

public interface RoleRepository {

	Role findByName(String name);

	Role saveRole(Role role);

}
