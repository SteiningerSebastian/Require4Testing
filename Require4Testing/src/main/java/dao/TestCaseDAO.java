package dao;
import entity.TestCase;

public class TestCaseDAO extends DAO<TestCase>{
	public TestCaseDAO() {
		super.setType(TestCase.class);
	}
}
