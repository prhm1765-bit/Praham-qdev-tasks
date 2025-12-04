class Student {

    // Private access modifier
    private int id;
    private String name;

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

        Student s = new Student();

        s.setId(101);
        s.setName("Praham");
        
        System.out.println("id = " + s.getId());
        System.out.println("Name = " + s.getName());
    }
}
