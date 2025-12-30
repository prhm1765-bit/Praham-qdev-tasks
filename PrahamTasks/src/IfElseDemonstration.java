import java.util.Scanner;

/**
* if-elseif-else code with user input and arithmetic operator
*/
public class IfElseDemonstration {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		int age = sc.nextInt();

		if (age < 18) {
			System.out.println("You are a child");
		} else if (age >= 18 && age <30) {
			System.out.println("You are Young");
		} else {
			System.out.println("You are Old");
		}
	}

}
