package entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class TestResult {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	protected int id;
	
	protected String resultDescription;
	
	@OneToOne
	@JoinColumn
	protected TestCase testCase;
	
	public TestResult() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getResultDescription() {
		return resultDescription;
	}
	
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}
	
	public TestCase getTestCase() {
		return testCase;
	}
	
	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}
}
