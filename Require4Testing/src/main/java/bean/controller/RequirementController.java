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
public class RequirementController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	protected RequirementService service;

	//Consult the matching comment about scope and services in the UserController.
	protected List<Requirement> requirements;

	public List<Requirement> getRequirements() {
		if(requirements == null)
			requirements = service.getElements();
		return requirements;
	}

	public void setRequirements(List<Requirement> requirements) {
		this.requirements = requirements;
	}

	public String edit(Requirement requirement) {
		return "requirementEdit?faces-redirect=true&requirement=" + requirement.getId();
	}
	
	public String delete(Requirement requirement) {
		service.delete(requirement);
		requirements.remove(requirement);
		return "";
	}

	public String add() {
		Requirement requirement = service.add();
		return "requirementEdit?faces-redirect=true&requirement=" + requirement.getId();
	}
}
