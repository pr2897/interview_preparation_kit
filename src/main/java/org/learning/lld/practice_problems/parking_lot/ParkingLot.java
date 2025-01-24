package org.learning.lld.practice_problems.parking_lot;

import lombok.ToString;
import org.learning.lld.practice_problems.parking_lot.VechileType.VechileType;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private static int spotId = 1;

    private List<ParkingSpot> spots;
    public ParkingLot(int totalSpots, int bike, int car, int truck) {
        spots = new ArrayList<>();

        for (int i=0;i<bike;i++, spotId++) spots.add(new ParkingSpot(spotId, VechileType.MOTORCYCLE));
        for (int i=0;i<car;i++, spotId++) spots.add(new ParkingSpot(spotId, VechileType.CAR));
        for (int i=0;i<bike;i++, spotId++) spots.add(new ParkingSpot(spotId, VechileType.TRUCK));
    }

    @Override
    public String toString() {
        String lotInfo = String.format("Parking lot: %s\n, total spot: %s\n", spotId, spots.size());
        StringBuilder stringBuilder = new StringBuilder(lotInfo);
        spots.forEach(e -> stringBuilder.append(e + "\n"));
        return stringBuilder.toString();
    }
}
