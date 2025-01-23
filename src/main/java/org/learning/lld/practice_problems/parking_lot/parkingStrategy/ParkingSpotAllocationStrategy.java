package org.learning.lld.practice_problems.parking_lot.parkingStrategy;

import org.learning.lld.practice_problems.parking_lot.ParkingSpot;
import org.learning.lld.practice_problems.parking_lot.VechileType.VechileType;

public interface ParkingSpotAllocationStrategy {
    ParkingSpot getAvailableParkingSpot(VechileType vechileType);
}
