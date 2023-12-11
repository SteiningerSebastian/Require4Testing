package bean.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bean.entity.AuthSession;
import entity.TestCase;
import entity.TestResult;
import entity.TestRun;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.TestCaseService;
import services.TestResultService;
import services.TestRunService;

@Named
@ViewScoped
public class TestResultController implements Serializable {
	private static final long serialVersionUID = 7454669499329653919L;

	@Inject
	protected TestResultService service;

	@Inject
	protected TestCaseService testCaseService;

	@Inject
	protected TestRunService testRunService;

	@Inject
	protected AuthSession authSession;

	protected List<TestCase> testCases;

	public TestResult getTestResult(TestCase testCase) {
		List<TestResult> results = service.getByTestCase(testCase);
		if (results.size() > 0)
			return results.getFirst();
		TestResult result = service.add();
		result.setTestCase(testCase);
		service.update(result);
		return result;
	}

	public List<TestCase> getTestCases() {
		if (testCases == null) {
			testCases = new ArrayList<TestCase>();
			for (TestRun testRun : testRunService.getByUser(authSession.getUser()))
				testCases.addAll(testCaseService.getByTestRun(testRun));
		}
		return testCases;
	}

	public void setTestCases(List<TestCase> testCases) {
		this.testCases = testCases;
	}

	public String edit(TestCase testCase, TestResult testResult) {
		return "testResultEdit?faces-redirect=true&testResult=%s&testCase=%s".formatted(testResult.getId(),
				testCase.getId());
	}
}