package adapter;

import domain.entity.Role;
import domain.repository.RoleRepository;
import lombok.AllArgsConstructor;
import mapper.RoleMapper;
import repository.RoleJpaRepository;

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
