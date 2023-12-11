package bean.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import entity.SystemUser;
import entity.TestCase;
import entity.TestRun;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.TestCaseService;
import services.TestRunService;
import services.UserService;

@Named
@ViewScoped
public class TestRunEditController implements Serializable {

	private static final long serialVersionUID = -3935015848113300390L;

	@Inject
	protected TestRunService service;

	@Inject
	protected TestCaseService testCaseService;

	@Inject
	protected UserService userService;

	private int testRunId;

	protected TestRun testRun;

	protected int assigneeId = -1;

	//Consult the matching comment about scope and services in the UserController.
	protected List<TestCase> testCases;

	public List<TestCase> getTestCases() {
		if(testCases == null)
			testCases = testCaseService.getElements();
		return testCases;
	}

	public void setTestCases(List<TestCase> testCases) {
		this.testCases = testCases;
	}

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

	public void setTestRunId(int testRunId) {
		this.testRunId = testRunId;
	}

	public int getAssigneeId() {
		if (assigneeId == -1)
			if (getTestRun().getAssignee() != null)
				assigneeId = getTestRun().getAssignee().getId();
			else
				assigneeId = 0;

		return assigneeId;
	}

	public void setAssigneeId(int assigneeId) {
		this.assigneeId = assigneeId;
	}

	public void addTestCase(TestCase testCase) {
		testCase.setTestRun(getTestRun());
		testCaseService.update(testCase);
	}

	public void removeTestCase(TestCase testCase) {
		if (testCase.getTestRun().getId() == getTestRun().getId())
			testCase.setTestRun(null);
		testCaseService.update(testCase);
	}

	public boolean hasTestRun(TestCase testCase) {
		if (testCase == null || testCase.getTestRun() == null)
			return false;
		return testCase.getTestRun().getId() == getTestRun().getId();
	}

	public String save() {
		getTestRun().setAssignee(userService.getById(getAssigneeId()));
		service.update(getTestRun());
		return "testRunManagement?faces-redirect=true";
	}

	public List<SystemUser> getTesters() {
		return userService.getElements().stream()
				.filter(e -> e.getRoles().stream().filter(r -> r.getName().equals("tester")).count() > 0)
				.collect(Collectors.toList());
	}
}
