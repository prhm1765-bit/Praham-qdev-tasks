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
     *
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

    public int compare(Students s1, Students s2) {
        return s1.name.compareTo(s2.name);
    }

}

/**
 * class for demonstration of comparable and comparator
 */
public class ComparatorComparableDemo {

    public static void main(String[] args) {
        ArrayList<Students> list = new ArrayList<>();

        list.add(new Students(3, "Rahul"));
        list.add(new Students(1, "Aman"));
        list.add(new Students(2, "Neha"));

        System.out.println("Sorting using Comparable");
        Collections.sort(list);

        for (Students s : list) {
            System.out.println(s.id + " " + s.name);
        }

        System.out.println();

        System.out.println("Sorting using Comparator");
        Collections.sort(list, new SortByName());

        for (Students s : list) {
            System.out.println(s.id + " " + s.name);
        }
    }

}
