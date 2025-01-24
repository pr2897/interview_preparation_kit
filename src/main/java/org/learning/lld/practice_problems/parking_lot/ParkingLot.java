package org.learning.lld.practice_problems.parking_lot;

import org.learning.lld.practice_problems.parking_lot.VechileType.VechileType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParkingLot {
    private static int spotId = 1;

    private List<ParkingSpot> spots;
    public ParkingLot(int totalSpots, int bike, int car, int truck) {
        spots = new ArrayList<>();

        for (int i=0;i<bike;i++, spotId++) spots.add(new ParkingSpot(spotId, VechileType.MOTORCYCLE));
        for (int i=0;i<car;i++, spotId++) spots.add(new ParkingSpot(spotId, VechileType.CAR));
        for (int i=0;i<bike;i++, spotId++) spots.add(new ParkingSpot(spotId, VechileType.TRUCK));
    }
}
