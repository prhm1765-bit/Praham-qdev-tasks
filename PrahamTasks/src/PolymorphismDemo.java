class MethodOverload {
    public void add(int a, int b) {
        int sum = a+b;
        System.out.println("Method with 2 parameter got caleed" + sum);
    }

    //Method Overloading
    public void add(int a, int b, int c) {
        int sum = a+b+c;
        System.out.println("Method with 3 parameter got caleed" + sum);
    }

    public void greet() {
        System.out.println("Hello, Good Morning!");
    }
}

class MethodOverride extends MethodOverload {
    //Method Overriding
    public void greet() {
        System.out.println("Hello, Good Evening!");
    }
}

public class PolymorphismDemo {
    public static void main(String[] args) {

        // Parent class with the object of Child class
        MethodOverload methodOverride = new MethodOverride();
        methodOverride.greet();

        MethodOverload methodOverload = new MethodOverload();
        methodOverload.add(1,2,3);
        methodOverload.add(1,2);

    }
}
