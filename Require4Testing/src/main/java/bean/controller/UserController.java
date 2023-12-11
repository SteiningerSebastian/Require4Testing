package bean.controller;

import java.io.Serializable;
import java.util.List;

import bean.entity.AuthSession;
import entity.SystemUser;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.UserService;

@Named
@ViewScoped
public class UserController implements Serializable {
	private static final long serialVersionUID = -5647834138463882382L;

	@Inject
	protected UserService service;

	@Inject
	protected AuthSession authSession;

	/**
	 * One might consider accessing the list of users directly in the View using the
	 * Service, this does not work for the primefaces dataTable because the
	 * reference for commandLink items is lost at sorting. Meaning that the
	 * commandLink references the wrong object if the containing bean is not
	 * ViewScoped, Services are not. This is why here the list of elements is read from
	 * the service and then used by the view. For more information consider reading
	 * the following discussion: https://forum.primefaces.org/viewtopic.php?t=7778
	 */
	protected List<SystemUser> users;

	public List<SystemUser> getUsers() {
		if (users == null)
			users = service.getElements();
		return users;
	}

	public void setUsers(List<SystemUser> users) {
		service.setElements(users);
	}

	public String edit(SystemUser user) {
		return "userEdit?faces-redirect=true&user=" + user.getId();
	}

	public String delete(SystemUser user) {
		service.delete(user);
		users.remove(user);
		return "";
	}

	public String add() {
		SystemUser user = service.add();
		return "userEdit?faces-redirect=true&user=" + user.getId();
	}
}
