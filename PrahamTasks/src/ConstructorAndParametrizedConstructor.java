class Sum {

    /**
     * Normal Constructor
     */
     Sum() {
         System.out.println("calling sum constructor");
     }

    /**
     * Parameterized Constructor
     * @param a
     * @param b
     */
     Sum(int a, int b) {
         int c=a+b;
         System.out.println("calling parametrized constructor:"+ c);
     }

}


public class ConstructorAndParametrizedConstructor {

    public static void main(String[] args){
        Sum sum=new Sum();
        Sum sum1=new Sum(10,20);
    }

}
