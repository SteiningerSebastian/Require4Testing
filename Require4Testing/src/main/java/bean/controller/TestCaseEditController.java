package bean.controller;
import java.io.Serializable;
import java.util.List;

import entity.Requirement;
import entity.Role;
import entity.SystemUser;
import entity.TestCase;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.RequirementService;
import services.TestCaseService;
import services.UserService;

@Named
@ViewScoped
public class TestCaseEditController implements Serializable {

	private static final long serialVersionUID = 6864977805807753509L;

	@Inject
	protected TestCaseService service;

	private int testCaseId;

	protected TestCase testCase;
	
	public TestCase getTestCase() {
		if (testCase == null)
			testCase = service.getById(testCaseId);
		return testCase;
	}

	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}

	public int getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(int testCaseId) {
		this.testCaseId = testCaseId;
	}

	public String save() {
		service.update(testCase);
		return "testCaseEditSelection?faces-redirect=true&requirement="+getTestCase().getRequirement().getId();
	}
}
