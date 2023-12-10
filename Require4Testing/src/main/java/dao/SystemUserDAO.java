package dao;
import java.util.List;

import entity.SystemUser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class SystemUserDAO extends DAO<SystemUser>{
	private static final long serialVersionUID = 1L;

	public SystemUserDAO() {
		setType(SystemUser.class);
	}
	
	public SystemUser identify(String username) {
		@SuppressWarnings("unchecked")
		List<SystemUser> user = entityManager.createQuery("select u from SystemUser as u where u.username = '%s'".formatted(username)).getResultList();
		if(user.isEmpty())
			return null;
		return user.getFirst();
	}
}
