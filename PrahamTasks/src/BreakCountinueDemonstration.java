public class BreakCountinueDemonstration {
    public static void main(String[] args) {

        //Code to print even number and stop the for loop at 8

        for(int j=0;j<10;j++){

            if(j==8){
                break;
            }

            if(j%2==0){
                continue;
            }

            System.out.println(j);

        }
    }
}
