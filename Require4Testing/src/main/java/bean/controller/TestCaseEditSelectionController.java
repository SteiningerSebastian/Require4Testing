package bean.controller;

import java.io.Serializable;
import java.util.List;

import entity.Requirement;
import entity.TestCase;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.RequirementService;
import services.TestCaseService;

@Named
@ViewScoped
public class TestCaseEditSelectionController implements Serializable {

	private static final long serialVersionUID = 2741102991350908961L;

	private int requirementId;

	protected Requirement requirement;

	@Inject
	protected TestCaseService service;

	@Inject
	protected RequirementService requirementService;

	// Consult the matching comment about scope and services in the UserController.
	protected List<TestCase> testCases;

	public List<TestCase> getTestCases() {
		if (testCases == null)
			testCases = service.getByRequirement(getRequirement());
		return testCases;
	}

	public void setTestCases(List<TestCase> testCases) {
		this.testCases = testCases;
	}

	public int getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(int requirementId) {
		this.requirementId = requirementId;
	}

	public Requirement getRequirement() {
		if (requirement == null)
			requirement = requirementService.getById(requirementId);
		return requirement;
	}

	public void setRequirement(Requirement requirement) {
		this.requirement = requirement;
	}

	public String edit(TestCase testCase) {
		return "testCaseEdit?faces-redirect=true&testCase=" + testCase.getId();
	}
	
	public String delete(TestCase testCase) {
		testCases.remove(testCase);
		service.delete(testCase);
		return "";
	}

	public String add() {
		TestCase testCase = service.add();
		testCase.setRequirement(getRequirement());
		service.update(testCase);

		return "testCaseEdit?faces-redirect=true&testCase=" + testCase.getId();
	}
}
