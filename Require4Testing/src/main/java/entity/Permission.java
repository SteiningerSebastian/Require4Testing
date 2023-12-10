package entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Permission {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	protected int id;

	@Column(unique = true)
	protected String name;

	protected String displayname;

	public Permission() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String name) {
		displayname = name;
	}
}
