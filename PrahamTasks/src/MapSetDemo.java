import java.util.*;

public class MapSetDemo {

    public static void main(String[] args) {
        System.out.println("\n--------------------Map------------------------\n");
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Praham");
        map.put(2, "Jayesh");
        map.put(3, "Jaydeep");

        System.out.println(map);
        System.out.println(map.get(2));
        System.out.println("All keys: " + map.keySet());
        System.out.println("All values: " + map.values());
        System.out.println("All entries: " + map.entrySet());
        System.out.println("New map after removing:" + map.remove(2));
        System.out.println(map);

        System.out.println("\n--------------------Set------------------------\n");
        Set<String> set = new HashSet<>();
        set.add("Praham");
        set.add("Jayesh");
        set.add("Jaydeep");
        System.out.println("Set:" + set);

        Iterator<String> it = set.iterator();

        while (it.hasNext()) {
            System.out.println("Current value of the set is:"+ it.next());
        }

        set.remove("Praham");
        System.out.println("Set after removing Praham is:" + set);
    }

}
