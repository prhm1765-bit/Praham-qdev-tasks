import java.io.FileWriter;
import java.util.Scanner;

public class FileCreation {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			FileWriter fileWriter = new FileWriter("myTestFile.txt");

			System.out.print("Enter your name: ");
			String name = scanner.nextLine();

			System.out.print("Enter your age: ");
			String age = scanner.nextLine();

			System.out.print("Enter your city: ");
			String city = scanner.nextLine();

			System.out.print("Enter your phone number: ");
			String phone = scanner.nextLine();

			fileWriter.write("Name: " + name + "\n");
			fileWriter.write("Age: " + age + "\n");
			fileWriter.write("City: " + city + "\n");
			fileWriter.write("Phone: " + phone + "\n");

			fileWriter.close();

			System.out.println("Data has been written successfully into myTestFile.txt");
		} catch (Exception e) {
			System.out.println("Something went wrong while writing to the file");
		}
		scanner.close();
	}

}
