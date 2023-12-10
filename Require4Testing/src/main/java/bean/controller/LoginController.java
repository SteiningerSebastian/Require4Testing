package bean.controller;
import java.io.Serializable;

import bean.entity.AuthSession;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.component.UIInput;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class LoginController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	protected AuthSession auth;

	public void validateUsername(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value != null && !auth.identify((String) value)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Wrong Username or Password!", "");
			System.out.print("Wrong Username or Password!");
			throw new ValidatorException(msg);
		}
	}

	public void postValidatePassword(ComponentSystemEvent event) throws ValidatorException {
		Object value = ((UIInput) event.getComponent()).getValue();

		if ((value == null || !auth.authenticate((String) value)) && auth.getUser() != null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Wrong Username or Password!", "");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(event.getComponent().getClientId(), msg);
		}
	}

	public String login() {
		if (auth.getIsAuthenticated()) {
			//Define where to redirect if a given permission is set and a hierarchy if a user has multiple permissions.
			if (auth.checkAuthorization("userManagement"))
				return "userManagement?faces-redirect=true";
			else if (auth.checkAuthorization("requirementManagement"))
				return "requirementManagement?faces-redirect=true";
			else if (auth.checkAuthorization("testResultManagement"))
				return "testResultManagement?faces-redirect=true";
			else if (auth.checkAuthorization("testRunManagement"))
				return "testRunManagement?faces-redirect=true";
			else if (auth.checkAuthorization("testCaseManagement"))
				return "testCaseManagement?faces-redirect=true";
			else
				return "";
		}
		return "";
	}
}
