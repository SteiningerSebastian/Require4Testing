package bean.controller;
import java.io.Serializable;
import java.util.List;

import entity.TestRun;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.TestRunService;

@Named
@ViewScoped
public class TestRunController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	protected TestRunService service;
	
	//Consult the matching comment about scope and services in the UserController.
	protected List<TestRun> testRuns;

	public List<TestRun> getTestRuns() {
		if(testRuns == null)
			testRuns = service.getElements();
		return testRuns;
	}

	public void setTestRuns(List<TestRun> testRuns) {
		this.testRuns = testRuns;
	}

	public String edit(TestRun testRun) {
		return "testRunEdit?faces-redirect=true&testRun=" + testRun.getId();
	}
	
	public String delete(TestRun testRun) {
		service.delete(testRun);
		testRuns.remove(testRun);
		return "";
	}

	public String add() {
		TestRun testRun = service.add();
		return "testRunEdit?faces-redirect=true&testRun=" + testRun.getId();
	}
}
