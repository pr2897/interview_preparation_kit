package org.learning.lld.practice_problems.parking_lot;

import lombok.Getter;
import org.learning.lld.practice_problems.parking_lot.VechileType.VechileType;

@Getter
public class TarrifRateCalculator {
    public static double getParkingTarrifRate(VechileType type) throws Exception {
        switch (type) {
            case CAR:
                return 5.0;
            case TRUCK:
                return 10.0;
            case MOTORCYCLE:
                return 2.0;
            default:
                throw new Exception("not a valid Vechile");
        }
    }
}
