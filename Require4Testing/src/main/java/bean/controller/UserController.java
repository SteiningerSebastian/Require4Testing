package bean.controller;
import java.io.Serializable;

import bean.entity.AuthSession;
import entity.SystemUser;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.UserService;

@Named
@ViewScoped
public class UserController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	protected UserService service;

	@Inject
	protected AuthSession authSession;

	public String edit(SystemUser user) {
		return "userEdit?faces-redirect=true&user=" + user.getId();
	}

	public String add() {
		SystemUser user = service.add();
		return "userEdit?faces-redirect=true&user=" + user.getId();
	}
}
