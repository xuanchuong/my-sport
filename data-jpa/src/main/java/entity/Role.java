package entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "role_id_seq")
	private Long id;

	private String name;

	public Role() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Role role = (Role) obj;
		return name.equals(role.name);
	}

	@Override
	public String toString() {
		return "Role [name=" + name + "]" + "[id=" +
				id + "]";
	}
}
