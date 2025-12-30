import java.util.Scanner;

public class FindArrayIndex {

	public static  void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int target = scanner.nextInt();
		int[] arr = {10,20,30,40,50,60,70,80,90,100};
		int index = -1;

		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == target) {
			index = i;
			break;
			}
		}

		if(index != -1) {
			System.out.println("Element " + target + " is present in the array at index " + index);
		} else {
			System.out.println("Element " + target + " is not present in the array at any index ");
		}
	}

}
