package org.learning.lld.practice_problems.parking_lot.VechileType;

import lombok.ToString;

@ToString
public class Motorcycle extends Vechile {
    public Motorcycle(String licenceNumber) {
        super(licenceNumber, VechileType.MOTORCYCLE);
    }
}
