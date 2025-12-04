class VariableDiffrence{

    int a=10;

    void printGlobalLocalVariable(){

        int a=20;

        System.out.println("Gloabal variable:" + this.a);
        System.out.println("Local variable:" + a);

    }
}

public class GlobalVsLocalVariable {
    public static void main(String[] args) {

        VariableDiffrence variableDiffrence=new VariableDiffrence();
        variableDiffrence.printGlobalLocalVariable();
    }
}
