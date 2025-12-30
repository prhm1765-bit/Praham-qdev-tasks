import java.util.*;

/**
 * Class for Studnet
 */
class Students implements Comparable<Students> {

	int id;
	String name;

	/**
	* @param id
	* @param name
	* All argument constructor
	*/
	Students(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	* @param s is parameter of student class
	* @return it will return integer value based ont he condition check
	*/
	public int compareTo(Students s) {
        return this.id - s.id;
    }

}

/**
* Class for sorting
*/
class SortByName implements Comparator<Students> {

	/**
	 * @param s1 student object 1
	 * @param s2 student object 2
	 * @return it will return integer value according to the comparision result
	 */
	public int compare(Students s1, Students s2) {
		return s1.name.compareTo(s2.name);
	}

}

/**
* class for demonstration of comparable and comparator
*/
public class ComparatorComparableDemo {

	public static void main(String[] args) {
	ArrayList<Students> studentList = new ArrayList<>();

		studentList.add(new Students(3, "Rahul"));
		studentList.add(new Students(1, "Aman"));
		studentList.add(new Students(2, "Neha"));

		System.out.println("Sorting using Comparable");
		Collections.sort(studentList);

		for (Students currentStudent : studentList) {
			System.out.println(currentStudent.id + " " + currentStudent.name);
		}
		System.out.println();
		System.out.println("Sorting using Comparator");
		Collections.sort(studentList, new SortByName());

		for (Students currentStudent : studentList) {
			System.out.println(currentStudent.id + " " + currentStudent.name);
		}
	}

}