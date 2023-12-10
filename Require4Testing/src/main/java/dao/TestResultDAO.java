package dao;
import entity.TestResult;

public class TestResultDAO extends DAO<TestResult>{
	public TestResultDAO() {
		super.setType(TestResult.class);
	}
}
