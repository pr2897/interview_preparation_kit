package org.learning.lld.practice_problems.parking_lot.VechileType;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
public class Car extends Vechile {
    public Car(String licenceNumber) {
        super(licenceNumber, VechileType.CAR);
    }
}
