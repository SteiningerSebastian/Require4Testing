package dao;
import javax.transaction.Transactional;

import entity.Requirement;
import jakarta.inject.Named;

@Named
@Transactional
public class RequirementDAO extends DAO<Requirement>{
	private static final long serialVersionUID = 6354581061483695161L;

	public RequirementDAO() {
		super.setType(Requirement.class);
	}
}
