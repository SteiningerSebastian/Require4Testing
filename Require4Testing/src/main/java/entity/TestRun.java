package entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class TestRun {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	protected int id;
	
	protected String name;
	
	protected String description;
	
	@OneToOne
	@JoinColumn
	protected SystemUser assignee;
	
	public TestRun() {
		
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
	
	public SystemUser getAsignee() {
		return this.assignee;
	}
	
	public void setAsignee(SystemUser asignee) {
		this.assignee = asignee;
	}
	
}
