package org.learning.lld.design_patterns.creational;

/**
 * Singleton solves the two problem at the same time:
 *  1. Ensure that a class has just a single instance.
 *  2. Provide a global access point to that instance.
 * </br>
 * </br>
 * <b>How to implement</b>
 * <pre>
 *     1. make default constructor private, to prevent other objects from using the new operator
 *        with the singleton class.
 *     2. Create a static creation method that acts as a constructor.
 *        Under the hood, this method calls the private constructor to create an object and
 *        saves it in a static field.
 *        All following calls to this method return the cached object.
 * </pre>
 */

class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() { }

    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }

        return instance;
    }
}

public class Singleton_Demo {
    public static void main(String[] args) {
        ThreadSafeSingleton inst = ThreadSafeSingleton.getInstance();
        System.out.println(inst);

        ThreadSafeSingleton inst2 = ThreadSafeSingleton.getInstance();
        System.out.println(inst);
    }
}
