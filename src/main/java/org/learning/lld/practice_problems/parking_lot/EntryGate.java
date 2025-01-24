package org.learning.lld.practice_problems.parking_lot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.learning.lld.practice_problems.parking_lot.VechileType.Vechile;
import org.learning.lld.practice_problems.parking_lot.parkingStrategy.ParkingSpotAllocationStrategy;

import java.util.Objects;

@AllArgsConstructor
@ToString
@Getter
public class EntryGate {
    private int gateId;
    private ParkingLot parkingLot;
    private ParkingSpotAllocationStrategy strategy;

    public Ticket getParkingTicket(EntryGate gate, Vechile vechile) throws Exception {
        var msg = String.format("%s entered through gate: %s", vechile, gate);
        System.out.println(msg);

        System.out.println("looking for parking spot...");
        ParkingSpot spot = strategy.getAvailableParkingSpot(parkingLot, vechile.getVechileType());

        if (Objects.isNull(spot)) throw new Exception("No Spots Available");

        return new Ticket(vechile, spot);
    }
}
