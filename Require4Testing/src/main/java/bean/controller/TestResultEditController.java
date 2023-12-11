package bean.controller;
import java.io.Serializable;
import entity.TestCase;
import entity.TestResult;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.TestCaseService;
import services.TestResultService;

@Named
@ViewScoped
public class TestResultEditController implements Serializable {

	private static final long serialVersionUID = -3892590790942097403L;

	@Inject
	protected TestResultService service;
	
	@Inject
	protected TestCaseService testCaseService;

	private int testCaseId;
	
	private int testResultId;

	protected TestCase testCase;
	
	protected TestResult testResult;
	
	public TestCase getTestCase() {
		if (testCase == null)
			testCase = testCaseService.getById(testCaseId);
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
	
	public TestResult getTestResult() {
		if (testResult == null)
			testResult = service.getById(testResultId);
		return testResult;
	}

	public void setTestResult(TestResult testResult) {
		this.testResult = testResult;
	}

	public int getTestResultId() {
		return testResultId;
	}

	public void setTestResultId(int testResultId) {
		this.testResultId = testResultId;
	}

	public String save() {
		service.update(getTestResult());
		testCaseService.update(getTestCase());
		return "testResultManagement?faces-redirect=true";
	}
}
