class StaticDemo{

    //Static variable
    static int staticCount=0;
    int normalCount=0;
    void incrementCount(){
        staticCount++;
        normalCount++;
        System.out.println("count="+staticCount + ",normal="+normalCount);
    }

    //static method
    static void printHello(){
        System.out.println("Calling static method without object directly by the class");
    }

    //static block
    static {
        System.out.println("It will be called beofre main method");
    }

}

public class StaticMethodVsVariableVsBlock {
    public static void main(String[] args) {

        //ststic varible will not be initialize when object is created with new keyword but the normal varible will initialze evrytime so staticVarible will 1,2,3 andnormal one will 1,1,1
        StaticDemo sd = new StaticDemo();
        sd.incrementCount();

        StaticDemo sd2 = new StaticDemo();
        sd2.incrementCount();

        StaticDemo sd3 = new StaticDemo();
        sd3.incrementCount();

        //calling static method without any object, directly by ht e class name
        StaticDemo.printHello();
    }
}
