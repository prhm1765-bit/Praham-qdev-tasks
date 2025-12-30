//Class to find area of rectangle
class Area {

	/**
	* Normal Constructor
	*/
	Area() {
         System.out.println("calling sum constructor");
     }

	/**
	* Parameterized Constructor
	* @param height is height of rectangle
	* @param widht is widht of rectangle
	*/
	Area(int height, int widht) {
		int area = height + widht;
		System.out.println("calling parametrized constructor:"+ area);
	}

}


public class ConstructorAndParametrizedConstructor {

	public static void main(String[] args){
		Area area=new Area();
		Area areaOfRectangle=new Area(10,20);
	}

}
