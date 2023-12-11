package services;

import java.util.List;

import entity.SystemUser;
import entity.TestCase;
import entity.TestRun;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import dao.TestRunDAO;

@Named
@RequestScoped
public class TestRunService extends EntityService<TestRun> {
	
	@Inject
	protected TestCaseService testCaseService;
	
	public TestRunService() {
		super(TestRun.class);
	}
	
	@Override
	public void delete(TestRun testRun) {
		for(TestCase testCase: testCaseService.getByTestRun(testRun)) {
			testCase.setTestRun(null);
			testCaseService.update(testCase);
		}
		
		super.delete(testRun);
	}
	
	public List<TestRun> getByUser(SystemUser user) {
		return ((TestRunDAO)dao).getByUser(user);
	}
}
