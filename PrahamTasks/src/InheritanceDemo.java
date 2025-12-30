/**
 * Paraent class
 */
class Animal {

	//Every animal eats
	void eat() {
		System.out.println("Animal eats food");
	}

}

// Sinle Inheritence
class Dog extends Animal {

	void bark() {
		System.out.println("Dog barks");
	}

}

class Puppy extends Dog {

	void weep() {
		System.out.println("Puppy weeps");
	}

}

//Hirarchical Inheritance
class Cat extends Animal {

	void meow() {
		System.out.println("Cat meows");
	}

}

//Hybrid Inheritance
interface Flyable {

	void fly();

}

interface Swimmable {

	void swim();

}

class Duck extends Animal implements Flyable, Swimmable {

	public void fly() {
		System.out.println("Duck can fly");
	}
	public void swim() {
		System.out.println("Duck can swim");
	}

}

public class InheritanceDemo {

	public static void main(String[] args) {
		System.out.println("Single Inheritance");
		Dog dog = new Dog();
		dog.eat();
		dog.bark();

		System.out.println(" Multilevel Inheritance");
		Puppy puppy = new Puppy();
		puppy.eat();
		puppy.bark();
		puppy.weep();

		System.out.println("Hierarchical Inheritance");
		Cat cat = new Cat();
		cat.eat();
		cat.meow();

		System.out.println("Hybrid Inheritance");
		Duck duck = new Duck();
		duck.eat();
		duck.fly();
		duck.swim();
	}

}
