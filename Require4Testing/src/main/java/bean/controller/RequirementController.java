package bean.controller;
import java.io.Serializable;

import entity.Requirement;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.RequirementService;

@Named
@ViewScoped
public class RequirementController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	protected RequirementService service;

	public String edit(Requirement requirement) {
		return "requirementEdit?faces-redirect=true&requirement=" + requirement.getId();
	}

	public String add() {
		Requirement requirement = service.add();
		return "requirementEdit?faces-redirect=true&requirement=" + requirement.getId();
	}
}
