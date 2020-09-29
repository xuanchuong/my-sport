package my.sport.data.jpa.repository;

import lombok.AllArgsConstructor;
import my.sport.data.jpa.mapper.RoleMapper;
import my.sport.domain.entity.Role;
import my.sport.domain.repository.RoleRepository;

@AllArgsConstructor
public class RoleRepositoryAdapter implements RoleRepository {

    private final RoleJpaRepository roleJpaRepository;
    private final RoleMapper roleMapper;

    @Override
    public Role findByName(String name) {
        return null;
    }

    @Override
    public Role saveRole(Role role) {
        return roleMapper.map(roleJpaRepository.save(roleMapper.map(role)));
    }
}
