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

    public void makePayment(Ticket ticket, PaymentStrategy paymentStrategy) throws Exception {
        var cost = getParkingCost(ticket);
        var result = paymentStrategy.pay(cost);

        if (result) {
            System.out.println("payment successful !!");
            System.out.println("clear to exit...");
        } else {
            System.out.println("Payment failed...");
            System.out.println("Retry payment....");
        }
    }
}
