public class EqualsVsDoubleEqualTo {

	public static void main(String[] args) {
		String str1 = new String("hii");
		String str2 = new String("hii");

		/**
		* Check with .equals
		*/
		if (str1.equals(str2)) {
			System.out.println(".equals got called");
		}

		/**
		* Check with ==
		*/
		if (str1 == str2) {
			// this block will not execute beacause both string are initialized with object(new keyword), so both has diffrent memory allocation
			System.out.println("== got called");
		}
	}

}
