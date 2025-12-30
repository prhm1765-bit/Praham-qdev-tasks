public class WhileDoWhileLoop {

	public static void main(String[] args) {
		int i=0;
		//1 to 100 with Whiule loop
		System.out.println("While Loop");
		while(i<=100){
			System.out.println(i);
			i++;
		}

		i=0;
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Do-While Loop");

		//1 to 100 with do-while
		do {
			System.out.println(i);
			i++;
		} while (i<=100);
	}

}
