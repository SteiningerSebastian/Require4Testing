package bean.controller;

import java.io.Serializable;
import java.util.List;

import entity.Requirement;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.RequirementService;

@Named
@ViewScoped
public class TestCaseController implements Serializable {

	private static final long serialVersionUID = 7135586004314800457L;

	@Inject
	protected RequirementService service;
	
	// Consult the matching comment about scope and services in the UserController.
	protected List<Requirement> requirements;

	public List<Requirement> getRequirements() {
		if (requirements == null)
			requirements = service.getElements();
		return requirements;
	}

	public void setRequirements(List<Requirement> requirements) {
		this.requirements = requirements;
	}

	public String edit(Requirement requirement) {
		return "testCaseEditSelection?faces-redirect=true&requirement=" + requirement.getId();
	}
}
