package services;

import java.util.List;

import entity.Requirement;
import entity.TestCase;
import entity.TestResult;
import entity.TestRun;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import dao.TestCaseDAO;

@Named
@RequestScoped
public class TestCaseService extends EntityService<TestCase> {
	
	@Inject
	TestResultService testResultService;
	
	public TestCaseService() {
		super(TestCase.class);
	}

	public List<TestCase> getByRequirement(Requirement requirement) {
		return ((TestCaseDAO) dao).getByRequirement(requirement);
	}
	
	public List<TestCase> getByTestRun(TestRun testRun) {
		return ((TestCaseDAO) dao).getByTestRun(testRun);
	}
	
	@Override
	public void delete(TestCase testCase) {
		testCase.setTestRun(null);
		
		for(TestResult testResult: testResultService.getByTestCase(testCase)){
			testResultService.delete(testResult);
		}
		
		super.delete(testCase);
	}
}
