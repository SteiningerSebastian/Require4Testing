package bean.controller;
import java.io.Serializable;
import java.util.List;

import entity.Requirement;
import entity.Role;
import entity.SystemUser;
import entity.TestRun;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.RequirementService;
import services.TestRunService;
import services.UserService;

@Named
@ViewScoped
public class TestRunEditController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	protected TestRunService service;

	private int testRunId;

	protected TestRun testRun;
	
	public TestRun getTestRun() {
		if (testRun == null)
			testRun = service.getById(testRunId);
		return testRun;
	}

	public void setTestRun(TestRun testRun) {
		this.testRun = testRun;
	}

	public int getTestRunId() {
		return testRunId;
	}

	public void setTestRunId(int requirementId) {
		this.testRunId = requirementId;
	}

	public String save() {
		service.update(testRun);
		return "testRunManagement?faces-redirect=true";
	}
}
