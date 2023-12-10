package services;

import entity.Permission;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class PermissionService extends EntityService<Permission> {
	public PermissionService() {
		super(Permission.class);
	}
}
