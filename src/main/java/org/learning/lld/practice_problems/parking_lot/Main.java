package org.learning.lld.practice_problems.parking_lot;

import org.learning.lld.practice_problems.parking_lot.VechileType.Car;
import org.learning.lld.practice_problems.parking_lot.VechileType.Vechile;
import org.learning.lld.practice_problems.parking_lot.parkingStrategy.NearestToEntry;
import org.learning.lld.practice_problems.parking_lot.payment_strategy.Cash;
import org.learning.lld.practice_problems.parking_lot.payment_strategy.UPI;

public class Main {
    public static void main(String[] args) throws Exception {
        ParkingLot parkingLot = new ParkingLot(100,50,40,10);
        EntryGate gate = new EntryGate(1, parkingLot, new NearestToEntry());
        Vechile car = new Car("BR-11-7383");
        Ticket ticket = gate.getParkingTicket(gate, car);
        Checkout checkout = new Checkout();
        checkout.makePayment(ticket, new Cash());
    }
}
