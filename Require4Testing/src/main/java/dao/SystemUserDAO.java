package dao;
import java.util.List;

import javax.transaction.Transactional;

import entity.SystemUser;
import jakarta.inject.Named;

@Named
@Transactional
public class SystemUserDAO extends DAO<SystemUser>{
	private static final long serialVersionUID = 3039250469265319786L;

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
