package fr.testjpa;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Animal")
public class Animal {

	@Id
	private int id;
	
	private String name;
	private String shout;
	
	public Animal () {
		
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public void setShout(String newShout) {
		shout = newShout;
	}
	
	public String getName() {
		return name;
	}
	
	public String getShout() {
		return shout;
	}
}
