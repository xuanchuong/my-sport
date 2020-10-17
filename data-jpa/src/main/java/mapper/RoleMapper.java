package mapper;

import domain.entity.Role;
import lombok.AllArgsConstructor;
import entity.JpaRole;

@AllArgsConstructor
public class RoleMapper {

    public JpaRole map(Role source) {
        JpaRole target = new JpaRole();
        target.setId(source.getId());
        target.setName(source.getName());
        return target;
    }

    public Role map(JpaRole source) {
        return Role.builder()
                .id(source.getId())
                .name(source.getName())
                .build();
    }
}
