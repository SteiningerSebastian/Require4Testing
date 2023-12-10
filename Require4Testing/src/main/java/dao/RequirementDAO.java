package dao;
import entity.Requirement;

public class RequirementDAO extends DAO<Requirement>{
	public RequirementDAO() {
		super.setType(Requirement.class);
	}
}
