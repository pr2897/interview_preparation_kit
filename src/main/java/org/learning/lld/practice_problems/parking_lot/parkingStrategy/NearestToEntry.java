package org.learning.lld.practice_problems.parking_lot.parkingStrategy;

import org.learning.lld.practice_problems.parking_lot.ParkingSpot;
import org.learning.lld.practice_problems.parking_lot.VechileType.VechileType;

public class NearestToEntry implements ParkingSpotAllocationStrategy {
    @Override
    public ParkingSpot getAvailableParkingSpot(VechileType vechileType) {
        System.out.println("finding available parking spot using NearestToEntry algorithm.");
        var spot = new ParkingSpot(12, vechileType);
        System.out.printf("spot allocated: %s\n", spot);
        return spot;
    }
}
