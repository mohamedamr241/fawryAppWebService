package softwareEngineering.fawryApp.models;

public class Person{
	private int id;
	private String name;
	
	public Person(int id, String name) {
		this.id=id;
		this.name=name;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(int i) {
		id=i;
	}
	public void setName(String n) {
		this.name=n;
	}
}