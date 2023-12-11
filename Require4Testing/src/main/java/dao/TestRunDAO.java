package dao;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import entity.SystemUser;
import entity.TestRun;
import jakarta.inject.Named;

@Named
@Transactional
public class TestRunDAO extends DAO<TestRun>{
	private static final long serialVersionUID = 2694680229006132183L;

	public TestRunDAO() {
		super.setType(TestRun.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<TestRun> getByUser(SystemUser user) {
		if(user == null)
			return new ArrayList<TestRun>();
		return (List<TestRun>)entityManager.createQuery("select e from TestRun as e WHERE assignee_id=%s".formatted(user.getId())).getResultList();
	}

}
