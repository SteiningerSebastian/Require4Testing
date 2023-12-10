package bean.entity;
import java.io.Serializable;

import entity.Permission;
import entity.Role;
import entity.SystemUser;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.UserService;

@Named
@SessionScoped
public class AuthSession implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	protected UserService service;
	
	protected SystemUser user;
	
	protected boolean isAuthenticated;
	
	public AuthSession() {
		
	}
	
	public SystemUser getUser(){
		return user;
	}
	
	public void setUser(SystemUser user) {
		this.user = user;
	}
	
	public boolean getIsAuthenticated() {
		return isAuthenticated;
	}
	
	public void setIsAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	
	public boolean identify(String username) {
		isAuthenticated = false;
		user = service.identify(username);
		return user != null;
	}
	
	public boolean authenticate(String password) {
		if(user != null) {
			isAuthenticated = service.authenticate(user, password);
			return isAuthenticated;
		}
		
		isAuthenticated = false;
		return false;
	}
	
	public boolean checkAuthorization(String permission) {
		if(user != null) {
			for(Role role : user.getRoles()) {
				for(Permission perm : role.getPermissions()) {
					if(perm.getName().equalsIgnoreCase(permission))
						return true;
				}
			}
		}
		return false;
	}
}
