package dao;
import javax.transaction.Transactional;

import entity.Permission;
import jakarta.inject.Named;

@Named
@Transactional
public class PermissionDAO extends DAO<Permission> {
	private static final long serialVersionUID = 1L;

	public PermissionDAO() {
		setType(Permission.class);
	}
}
