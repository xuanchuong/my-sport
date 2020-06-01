package my.sport.data.jpa.repository;

import lombok.AllArgsConstructor;
import my.sport.data.jpa.mapper.RoleMapper;
import my.sport.domain.entity.Role;
import my.sport.domain.repository.RoleRepository;

@AllArgsConstructor
public class RoleRepositoryAdapter implements RoleRepository {

    private RoleJpaRepository roleJpaRepository;
    private RoleMapper roleMapper;

    @Override
    public Role findByName(String name) {
        return null;
    }
}
