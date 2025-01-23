package org.learning.lld.practice_problems.parking_lot;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.learning.lld.practice_problems.parking_lot.VechileType.Vechile;
import org.learning.lld.practice_problems.parking_lot.VechileType.VechileType;

@Getter
@ToString
public class ParkingSpot {
    private final int spotNumber;
    private final VechileType vechileType;

    @Setter
    @Getter
    private Vechile parkedVechile;

    public ParkingSpot(int spotNumber, VechileType vechileType) {
        this.spotNumber  = spotNumber;
        this.vechileType = vechileType;
    }
}
