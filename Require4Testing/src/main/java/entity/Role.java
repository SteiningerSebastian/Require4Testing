package entity;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	protected int id;

	@Column(unique = true)
	protected String name;

	protected String displayname;

	@ManyToMany
	protected List<Permission> permissions;

	public Role() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String name) {
		displayname = name;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Role role = (Role) obj;
		return name.equals(role.name) && id == role.id;
	}

	@Override
	public int hashCode() {
		return "%s_%n".formatted(name, id).hashCode(); // replace with your fields
	}

}
