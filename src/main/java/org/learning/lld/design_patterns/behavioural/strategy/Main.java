package org.learning.lld.design_patterns.behavioural.strategy;

interface PaymentStrategy {
    void processPayment(double amount);
}

class UPIPaymentStrategy implements PaymentStrategy {

    @Override
    public void processPayment(double amount) {
        System.out.printf("Rs. %.2f processed using UPI\n", amount);
    }
}

class DebitCardPaymentStrategy implements PaymentStrategy {

    @Override
    public void processPayment(double amount) {
        System.out.printf("Rs. %.2f processed via debit card\n", amount);
    }
}

class PaymentService {
    private PaymentStrategy strategy;

    public PaymentService(PaymentStrategy strategy) {this.strategy = strategy; };

    public void pay(double amount) {
        this.strategy.processPayment(amount);
    }
}

public class Main {
    public static void main(String[] args) {
        PaymentStrategy debitCardPaymentStrategy = new UPIPaymentStrategy();
        PaymentService paymentService = new PaymentService(debitCardPaymentStrategy);
        paymentService.pay(20);
    }
}
