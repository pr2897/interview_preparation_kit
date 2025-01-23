package org.learning.lld.practice_problems.parking_lot.payment_strategy;

public class UPI implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        String msg = String.format("paid $%.2f successfully via UPI", amount);
        System.out.println(msg);
        return true;
    }
}
