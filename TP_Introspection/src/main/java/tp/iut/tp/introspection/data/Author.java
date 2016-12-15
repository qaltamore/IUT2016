package tp.iut.tp.introspection.data;

public class Author {

	public String firstName;
	public String lastName;
	
	public void setFirstName(String newFirstName) {
		this.firstName = newFirstName;
	}
	
	public void setLastName(String newLastName) {
		this.lastName = newLastName;
	}
	
	public void setAuthor(String newFirstName, String newLastName) {
		this.firstName = newFirstName;
		this.lastName = newLastName;
	}
	
}
