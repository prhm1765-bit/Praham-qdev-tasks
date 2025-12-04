public class WrapperClassCompare {
    public static void main(String[] args) {

        // code to compare wrapper classes
        int a=10;
        int d=10;

        Integer b= Integer.valueOf(10);
        Integer c=Integer.valueOf(10);

        if(b==c){
            System.out.println("They are equal");
        }
    }
}
