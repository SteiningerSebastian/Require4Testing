package bean.controller;
import java.io.Serializable;
import java.util.List;

import entity.Role;
import entity.SystemUser;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.UserService;

@Named
@ViewScoped
public class UserEditController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	protected UserService service;

	private int userId;

	protected SystemUser user;

	protected List<Role> roles;
	
	protected String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public SystemUser getUser() {
		if (user == null)
			user = service.getById(userId);
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public List<Role> getRoles() {
		if (roles == null)
			roles = getUser().getRoles();
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
		getUser().setRoles(roles);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String save() {
		service.setPassword(user, password);
		service.update(user);
		return "userManagement?faces-redirect=true";
	}
}
