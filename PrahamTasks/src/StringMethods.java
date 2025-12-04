public class StringMethods {

    public static void main(String[] args) {
        String name = "Praham";
        String surname = "Patel";

        //Length
        int len = name.length();
        System.out.println("length of string 1 is:" + len);

        //UpperCase LowerCase
        String upperCaseName = name.toUpperCase();
        String lowerCaseName = name.toLowerCase();
        System.out.println("upperCase is:" + upperCaseName);
        System.out.println("lowerCase is:" + lowerCaseName);

        //chatAt(index)
        char initial = name.charAt(0);
        System.out.println("Initial is:" + initial);

        //equals
        System.out.println("name and surname are the same: "+ name.equals(surname));

        // indexOf
        System.out.println("Index of h is: "+ name.indexOf("h"));

        // replace
        System.out.println("New Name is: "+ name.replace("m", "p"));

        // sub string
        System.out.println("First three letters of yout name is: "+ name.substring(0,3));

        // parse int
        String str = "1000";
        int num = Integer.parseInt(str);
        System.out.println("number is:" + num);
    }
}
