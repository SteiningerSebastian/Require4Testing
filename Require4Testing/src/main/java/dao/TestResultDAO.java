package dao;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import entity.TestCase;
import entity.TestResult;
import jakarta.inject.Named;

@Named
@Transactional
public class TestResultDAO extends DAO<TestResult>{
	private static final long serialVersionUID = -1107297146319756782L;

	public TestResultDAO() {
		super.setType(TestResult.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<TestResult> getByTestCase (TestCase testCase){
		if(testCase == null)
			return new ArrayList<TestResult>();
		return (List<TestResult>)entityManager.createQuery("select e from TestResult as e WHERE testCase_id=%s".formatted(testCase.getId())).getResultList();
	}
}
