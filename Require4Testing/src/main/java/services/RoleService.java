package services;

import entity.Role;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class RoleService extends EntityService<Role> {
	public RoleService() {
		super(Role.class);
	}
}
