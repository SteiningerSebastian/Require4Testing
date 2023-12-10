package entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TestCase {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	protected int id;

	protected String name;

	protected String description;
	
	protected int statusCode;
	
	@ManyToOne
	@JoinColumn
	protected Requirement requirement;
	
	@ManyToOne
	@JoinColumn
	protected TestRun testRun;
	
	public TestCase() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getStatusCode(){
		return statusCode;
	}
	
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public Requirement getRequirement() {
		return this.requirement;
	}
	
	public void setRequirement(Requirement requirement) {
		this.requirement = requirement;
	}
	
	public TestRun getTestRun() {
		return this.testRun;
	}
	
	public void setTestRun(TestRun testRun) {
		this.testRun = testRun;
	}
}
