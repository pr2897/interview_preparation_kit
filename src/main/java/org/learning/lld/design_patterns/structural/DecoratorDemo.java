package org.learning.lld.design_patterns.structural;

interface Pizza {
    double price();
}

class BasePizza implements Pizza {
    public double price() {
        return 10;
    }
}

class CheezeBasePizza implements Pizza {
    public double price() {
        return 15;
    }
}

class CheeseTopping implements Pizza {
    private final Pizza basePizza;
    public CheeseTopping(Pizza pizza) {
        this.basePizza = pizza;
    }

    public double price() {
        return this.basePizza.price() + 2;
    }
}

class PannerTopping implements Pizza {
    private final Pizza basePizza;
    public PannerTopping(Pizza pizza) {
        this.basePizza = pizza;
    }
    public double price() {
        return this.basePizza.price() + 5;
    }
}


public class DecoratorDemo {
    public static void main(String[] args) {
        Pizza pizza =  new CheeseTopping(new PannerTopping(new BasePizza()));
        System.out.println(pizza.price());
    }
}
