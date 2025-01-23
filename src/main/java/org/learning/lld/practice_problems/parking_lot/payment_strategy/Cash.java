package org.learning.lld.practice_problems.parking_lot.payment_strategy;

public class Cash implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        String msg = String.format("paid $%.2f successfully via Cash", amount);
        System.out.println(msg);
        return true;
    }
}
