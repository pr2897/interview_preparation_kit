package org.learning.lld.practice_problems.parking_lot.payment_strategy;

public class Cash implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        String msg = String.format("paying $%.2f via Cash", amount);
        System.out.println(msg);
        return true;
    }
}
