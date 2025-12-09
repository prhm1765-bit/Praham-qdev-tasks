import java.io.FileWriter;
import java.util.Scanner;

public class FileCreation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            FileWriter fw = new FileWriter("myTestFile.txt");

            System.out.print("Enter your name: ");
            String name = sc.nextLine();

            System.out.print("Enter your age: ");
            String age = sc.nextLine();

            System.out.print("Enter your city: ");
            String city = sc.nextLine();

            System.out.print("Enter your phone number: ");
            String phone = sc.nextLine();

            fw.write("Name: " + name + "\n");
            fw.write("Age: " + age + "\n");
            fw.write("City: " + city + "\n");
            fw.write("Phone: " + phone + "\n");

            fw.close();

            System.out.println("Data has been written successfully into myTestFile.txt");
        } catch (Exception e) {
            System.out.println("Something went wrong while writing to the file");
        }
        sc.close();
    }

}
