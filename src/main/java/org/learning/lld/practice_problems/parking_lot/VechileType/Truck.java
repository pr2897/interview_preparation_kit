package org.learning.lld.practice_problems.parking_lot.VechileType;

import lombok.ToString;

@ToString
public class Truck extends Vechile {
    public Truck(String licenceNumber) {
        super(licenceNumber, VechileType.TRUCK);
    }
}
