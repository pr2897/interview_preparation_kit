package org.learning.lld.design_patterns.creational;

@FunctionalInterface
interface Animal {
    public void speak();
}

class Dog implements Animal {
    @Override
    public void speak() {
        System.out.println("woof woof...");
    }
}

class Cat implements Animal {
    @Override
    public void speak() {
        System.out.println("meow meow....");
    }
}

class AnimalFactory {
    private AnimalFactory() { }

    public static Animal getAnimal(String type) {
        if (type == null) return null;

        return switch (type) {
            case "cat" -> new Cat();
            case "dog" -> new Dog();
            default -> null;
        };
    }
}

public class Factory {
    public static void main(String[] args) {
        Animal dog = AnimalFactory.getAnimal("dog");
        dog.speak();

        Animal cat = AnimalFactory.getAnimal("cat");
        cat.speak();
    }
}
