import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListIteratorDemo {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("Hello");
		list.add("World");
		list.add("Praham");

		System.out.println("List:" + list);

		Iterator<String> iterator = list.iterator();

		while (iterator.hasNext()) {
			String currentValue = iterator.next();
			System.out.println("Current value of the list through the Iterator is: " + currentValue);
		}
	}

}
