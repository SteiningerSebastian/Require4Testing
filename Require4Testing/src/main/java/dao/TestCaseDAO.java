package dao;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import entity.Requirement;
import entity.TestCase;
import entity.TestRun;
import jakarta.inject.Named;

@Named
@Transactional

public class TestCaseDAO extends DAO<TestCase>{
	private static final long serialVersionUID = 4132832263033827895L;

	public TestCaseDAO() {
		super.setType(TestCase.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<TestCase> getByRequirement(Requirement requirement) {
		if(requirement == null)
			return new ArrayList<TestCase>();
		return (List<TestCase>)entityManager.createQuery("select e from TestCase as e WHERE requirement_id=%s".formatted(requirement.getId())).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<TestCase> getByTestRun(TestRun testRun) {
		if(testRun == null)
			return new ArrayList<TestCase>();
		return (List<TestCase>)entityManager.createQuery("select e from TestCase as e WHERE testRun_id=%s".formatted(testRun.getId())).getResultList();
	}
}
