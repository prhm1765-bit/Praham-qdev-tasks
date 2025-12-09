public class StarPattern {

    public static void main(String[] args) {
        /*
        * Code to print the below pattern with for loop
        *       *
        *       * *
        *       * * *
        *       * * * *
        *       * * * * *
        *
        * */
        char a = '*';

        for (int i=0; i<=4; i++) {
            for (int j=0; j<=i; j++) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }

}
