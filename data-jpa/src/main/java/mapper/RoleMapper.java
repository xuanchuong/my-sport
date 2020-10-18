package mapper;

import entity.Role;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoleMapper {

    public Role map(domain.entity.Role source) {
        Role target = new Role();
        target.setId(source.getId());
        target.setName(source.getName());
        return target;
    }

    public domain.entity.Role map(Role source) {
        return domain.entity.Role.builder()
                .id(source.getId())
                .name(source.getName())
                .build();
    }
}
