package org.learning.lld.practice_problems.parking_lot;

import org.learning.lld.practice_problems.parking_lot.payment_strategy.PaymentStrategy;

import java.time.Duration;
import java.time.LocalDateTime;

public class Checkout {
    public double getParkingCost(Ticket ticket) throws Exception {
        LocalDateTime currentTime = LocalDateTime.now();
        Duration elapsedTime = Duration.between(ticket.getEntryTime(), currentTime);
        long elapsedTimeInMinutes = elapsedTime.toMinutes();
        double parkingRate = TarrifRateCalculator.getParkingTarrifRate(ticket.getVechile().getVechileType());
        return parkingRate * elapsedTimeInMinutes;
    }

    public boolean makePayment(Ticket ticket, PaymentStrategy paymentStrategy) throws Exception {
        var cost = getParkingCost(ticket);
        return paymentStrategy.pay(cost);
    }
}
