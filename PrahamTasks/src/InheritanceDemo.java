// Parent Class
class Animal {
    void eat() {
        System.out.println("Animal eats food");
    }
}

// Single Inheritance
class Dog extends Animal {
    void bark() {
        System.out.println("Dog barks");
    }
}

// Multilevel Inheritance
class Puppy extends Dog {
    void weep() {
        System.out.println("Puppy weeps");
    }
}

// Hierarchical Inheritance
class Cat extends Animal {
    void meow() {
        System.out.println("Cat meows");
    }
}

// Hybrid Inheritance using Interfaces
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
        Dog d = new Dog();
        d.eat();
        d.bark();

        System.out.println(" Multilevel Inheritance");
        Puppy p = new Puppy();
        p.eat();
        p.bark();
        p.weep();

        System.out.println("Hierarchical Inheritance");
        Cat c = new Cat();
        c.eat();
        c.meow();

        System.out.println("Hybrid Inheritance");
        Duck dk = new Duck();
        dk.eat();
        dk.fly();
        dk.swim();
    }
}
