package org.learning.lld.practice_problems.parking_lot.VechileType;

import lombok.ToString;

@ToString
public class Car extends Vechile {
    public Car(String licenceNumber) {
        super(licenceNumber, VechileType.CAR);
    }
}
