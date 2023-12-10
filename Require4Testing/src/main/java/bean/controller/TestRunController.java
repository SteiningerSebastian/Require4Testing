package bean.controller;
import java.io.Serializable;

import entity.Requirement;
import entity.TestRun;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.RequirementService;
import services.TestRunService;

@Named
@ViewScoped
public class TestRunController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	protected TestRunService service;

	public String edit(TestRun testRun) {
		return "testRunEdit?faces-redirect=true&requirement=" + testRun.getId();
	}

	public String add() {
		TestRun testRun = service.add();
		return "testRunEdit?faces-redirect=true&requirement=" + testRun.getId();
	}
}
