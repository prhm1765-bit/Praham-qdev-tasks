import java.util.Scanner;

class CheckAge{
    public void checkAge(int age) throws ArithmeticException {
        if (age < 18) {
            throw new IllegalArgumentException();
        }
        else {
            System.out.println("You are elidgible  " + age);
        }
    }
}

public class ExceptionDemo {
    public static void main(String[] args) {
        try {

            CheckAge checkAge = new CheckAge();
            checkAge.checkAge(17);
            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt();
            int b = sc.nextInt();

            if(b==0){
                throw new ArithmeticException();
            }
            int c = a/b;
        }
        catch(ArithmeticException e){
            System.out.println("Divide By Zero Exception" + e);
        }catch (IllegalArgumentException e){
            System.out.println("Invalid Age Exception" + e);
        }

        finally{
            System.out.println("Finally block will always execute");
        }
    }
}
