class Student {

	// Private access modifier
	private int id;
	private String name;

	/**
	*Getter Setters
	*/
	public void setId(int i) {
		id = i;
	}

	public void setName(String n) {
		name = n;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}

public class EncapsulationDemo {

	public static void main(String[] args) {
		Student student = new Student();
		student.setId(101);
		student.setName("Praham");
		System.out.println("id = " + student.getId());
		System.out.println("Name = " + student.getName());
	}

}
