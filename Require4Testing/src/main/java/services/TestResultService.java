package services;

import java.util.List;

import entity.TestCase;
import entity.TestResult;
import dao.TestResultDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class TestResultService extends EntityService<TestResult> {
	public TestResultService() {
		super(TestResult.class);
	}

	public List<TestResult> getByTestCase(TestCase testCase) {
		return ((TestResultDAO)dao).getByTestCase(testCase);
	}
}
