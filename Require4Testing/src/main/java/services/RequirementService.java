package services;

import entity.Requirement;
import entity.TestCase;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class RequirementService extends EntityService<Requirement> {
	
	@Inject
	TestCaseService testCaseService;
	
	public RequirementService() {
		super(Requirement.class);
	}
	
	@Override
	public void delete(Requirement requirement) {
		for(TestCase testCase : testCaseService.getByRequirement(requirement)) {
			testCaseService.delete(testCase);
		}
		
		super.delete(requirement);
	}
}
