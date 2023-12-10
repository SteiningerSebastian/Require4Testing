package dao;
import javax.transaction.Transactional;

import entity.Role;
import jakarta.inject.Named;

@Named
@Transactional
public class RoleDAO extends DAO<Role> {
	private static final long serialVersionUID = 1L;

	public RoleDAO() {
		setType(Role.class);
	}
}
