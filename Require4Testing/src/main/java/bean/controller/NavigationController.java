package bean.controller;

import bean.entity.AuthSession;
import entity.Permission;
import entity.Role;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class NavigationController {

	@Inject
	protected AuthSession authSession;

	public String navigateToRequirements() {
		return "requirementManagement?faces-redirect=true";
	}

	public String navigateToTestRun() {
		return "testRunManagement?faces-redirect=true";
	}

	public String navigateToTestCase() {
		return "testCaseManagement?faces-redirect=true";
	}

	public String navigateToUserManagement() {
		System.out.print("redirect");
		return "userManagement?faces-redirect=true";
	}

	public String navigateToTests() {
		return "testResultManagement?faces-redirect=true";
	}

	public boolean isPage(String path) {
		return FacesContext.getCurrentInstance().getViewRoot().getViewId().endsWith(path);
	}

	public boolean navigationAllowed(String path) {
		if (authSession.getUser() != null)
			for (Role r : authSession.getUser().getRoles()) {
				for (Permission p : r.getPermissions()) {
					if (path.endsWith(p.getName() + ".xhtml"))
						return true;
				}
			}

		return false;
	}
}
