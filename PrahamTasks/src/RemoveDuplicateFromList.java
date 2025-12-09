import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class RemoveDuplicateFromList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "A", "A", "C", "C", "E", "E", "E"));

        for (int i = 0; i < list.size(); i++) {
            for(int j = i +1 ; j < list.size(); j++){
                if(list.get(i).equals(list.get(j))){
                    list.remove(j);
                    j--;
                }
            }
        }
        System.out.println("List:" + list);
    }

}
