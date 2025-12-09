import java.util.Scanner;

/**
 * Class for EMI calculation Process
 */
class Emi {

    double P;   // Loan Amount
    double R;   // Interest Rate
    int T;      // Time in years

    /**
     * Method for Getting the inputted data
     */
    void getInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Loan Amount: ");
        P = sc.nextDouble();

        System.out.print("Enter Annual Interest Rate (%): ");
        R = sc.nextDouble();

        System.out.print("Enter Time (in years): ");
        T = sc.nextInt();
    }

    /**
     * Method for calculating the EMI
     */
    void calculateEmi() {
        double monthlyRate = R / (12 * 100);
        int months = T * 12;

        double EMI = (P * monthlyRate * Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);

        System.out.println("Monthly EMI = " + EMI);
    }

}


public class EmiCalculator {

    public static void main(String[] args) {

        Emi obj = new Emi();
        obj.getInput();
        obj.calculateEmi();
    }

}
