import java.util.Scanner;

/**
 * Class for EMI calculation Process
 */
class Emi {

	double Principle;// Loan Amount
	double RateofInterest;// Interest Rate
	int Time;// Time in years

	/**
	* Method for Getting the inputted data
	*/
	void getInput() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Loan Amount: ");
		Principle = sc.nextDouble();
		System.out.print("Enter Annual Interest Rate (%): ");
		RateofInterest = sc.nextDouble();
		System.out.print("Enter Time (in years): ");
		Time = sc.nextInt();
	}

	/**
	* Method for calculating the EMI
	*/
	void calculateEmi() {
		double monthlyRate = RateofInterest / (12 * 100);
		int months = Time * 12;
		double EMI = (Principle * monthlyRate * Math.pow(1 + monthlyRate, months)) / (Math.pow(1 + monthlyRate, months) - 1);
		System.out.println("Monthly EMI = " + EMI);
	}

}


public class EmiCalculator {

	public static void main(String[] args) {
		Emi emi = new Emi();
		emi.getInput();
		emi.calculateEmi();
	}

}