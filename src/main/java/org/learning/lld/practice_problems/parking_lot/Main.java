package org.learning.lld.practice_problems.parking_lot;

import org.learning.lld.practice_problems.parking_lot.VechileType.Car;
import org.learning.lld.practice_problems.parking_lot.VechileType.Vechile;
import org.learning.lld.practice_problems.parking_lot.parkingStrategy.NearestToEntry;
import org.learning.lld.practice_problems.parking_lot.payment_strategy.UPI;

public class Main {
    public static void main(String[] args) throws Exception {
        EntryGate gate = new EntryGate(1, new NearestToEntry());
        Vechile car = new Car("BR-11-7383");
        Ticket ticket = gate.getParkingTicket(gate, car);
        Checkout checkout = new Checkout();
        if (checkout.makePayment(ticket, new UPI())) System.out.println("exited");
        else System.out.println("payment failed");
    }
}
