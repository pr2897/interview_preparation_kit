package org.learning.lld.design_patterns.behavioural.observer;

import java.util.ArrayList;
import java.util.List;

interface Observer<T> {
    public void update(T data);
}

class Mobile implements Observer<Double> {
    private String owner;

    public Mobile(String owner){
        this.owner = owner;
    }

    public void update(Double data) {
        System.out.printf("owner updated. current temp: %f\n", data);
    }
}

class TV implements Observer<Double> {

    public void update(Double data) {
        System.out.printf("TV notified. current temp: %f\n", data);
    }
}

abstract class Subject<T> {
    List<Observer<T>> observers = new ArrayList<>();

    public void addObserver(Observer<T> observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer<T> observer) {
        this.observers.remove(observer);
    }
}

class WeatherStation extends Subject<Double> {
    private double temperature;

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        this.observers.forEach(observer -> {
            observer.update(temperature);
        });
    }

    public double getTemperature() { return this.temperature; }
}

public class Main {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();

        Observer observer = new Mobile("Piyush");
        Observer observer1 = new Mobile("Ayush");
        Observer tv = new TV();

        station.addObserver(observer);
        station.addObserver(observer1);
        station.addObserver(tv);

        station.setTemperature(12);
        station.removeObserver(tv);
        station.setTemperature(24);
    }
}
