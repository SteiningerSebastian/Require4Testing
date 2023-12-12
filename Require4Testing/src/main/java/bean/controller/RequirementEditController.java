package bean.controller;
import java.io.Serializable;

import entity.Requirement;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.RequirementService;

@Named
@ViewScoped
public class RequirementEditController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	protected RequirementService service;

	private int requirementId;

	protected Requirement requirement;
	
	public Requirement getRequirement() {
		if (requirement == null)
			requirement = service.getById(requirementId);
		return requirement;
	}

	public void setRequirement(Requirement requirement) {
		this.requirement = requirement;
	}

	public int getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(int requirementId) {
		this.requirementId = requirementId;
	}

	public String save() {
		service.update(requirement);
		return "requirementManagement?faces-redirect=true";
	}
}
