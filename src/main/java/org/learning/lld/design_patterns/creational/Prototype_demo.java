package org.learning.lld.design_patterns.creational;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>Prototype is a creational design pattern that lets you copy existing objects without making your code dependent
 * on their classes.</b>
 * <hr/>
 * <b>The Prototype pattern delegates the cloning process to the actual objects that are being cloned.</b>
 */

interface Prototype {
    Prototype clone();
}

@Getter
class Car implements Prototype {
    private String model;
    private String engine;

    public Car(String model, String engine) {
        this.model = model;
        this.engine = engine;
    }

    @Override
    public Prototype clone() {
        return new Car(this.model, this.engine);
    }
}

public class Prototype_demo {
    public static void main(String[] args) {
        Car originalCar = new Car("Sedan", "v6");
        Car clonedCar = (Car)originalCar.clone();

        System.out.println(originalCar);
        System.out.println(originalCar.getEngine() + " "  + originalCar.getModel());

        System.out.println(clonedCar);
        System.out.println(clonedCar.getEngine() + " "  + clonedCar.getModel());
    }
}
