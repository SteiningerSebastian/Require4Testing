package dao;
import entity.TestRun;

public class TestRunDAO extends DAO<TestRun>{
	public TestRunDAO() {
		super.setType(TestRun.class);
	}
}
