class StaticDemo {

	//Static variable
	static int staticCount=0;
	int normalCount=0;

	void incrementCount() {
		staticCount++;
		normalCount++;
		System.out.println("count="+staticCount + ",normal="+normalCount);
	}

	//static method
	static void printHello() {
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
		StaticDemo staticDemo = new StaticDemo();
		staticDemo.incrementCount();

		StaticDemo staticDemo1 = new StaticDemo();
		staticDemo1.incrementCount();

		StaticDemo staticDemo2 = new StaticDemo();
		staticDemo2.incrementCount();

		//calling static method without any object, directly by ht e class name
		StaticDemo.printHello();
	}

}
