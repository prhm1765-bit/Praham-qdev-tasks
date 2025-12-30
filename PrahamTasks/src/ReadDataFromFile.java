import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class ReadDataFromFile {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter file name to read: ");
		String fileName = sc.nextLine();
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			System.out.println("Data inside the file is");

			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				if (line.contains("Name")) {
					System.out.println("This line contains a name");
				}
			}
			bufferedReader.close();
			fileReader.close();
		} catch (Exception e) {
			System.out.println("File not found or error while reading the file");
		}
		sc.close();
	}

}

