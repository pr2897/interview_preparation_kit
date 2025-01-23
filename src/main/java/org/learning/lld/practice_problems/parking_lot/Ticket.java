package org.learning.lld.practice_problems.parking_lot;

import lombok.Getter;
import lombok.ToString;
import org.learning.lld.practice_problems.parking_lot.VechileType.Vechile;

import java.time.LocalDateTime;
import java.util.UUID;

@ToString
@Getter
public class Ticket {
    private String ticketId;
    private Vechile vechile;
    private ParkingSpot parkingSpot;
    private LocalDateTime entryTime;

    public Ticket(Vechile vechile, ParkingSpot parkingSpot) {
        this.vechile = vechile;
        this.parkingSpot = parkingSpot;
        this.ticketId = UUID.randomUUID().toString();
        this.entryTime = LocalDateTime.now().minusMinutes(30);
    }
}
