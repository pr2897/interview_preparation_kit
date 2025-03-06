package org.learning.lld.revision_practice.vendingMachine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

enum Coin {
    PENNY(0.01),
    NICKEL(0.05),
    DIME(0.1),
    QUARTER(0.25);

    private final double value;

    Coin(double value) {
        this.value = value;
    }

    public double getValue() { return value; }

}
enum Note {
    ONE(1),
    FIVE(5),
    TEN(10),
    TWENTY(20);

    private final int value;

    Note(int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}

@AllArgsConstructor
@Getter
class Product {
    private final String name;
    private final double price;
}

class Inventory {
    private final Map<Product, Integer> products;

    public Inventory() {
        products = new ConcurrentHashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        products.put(product, quantity);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void updateQuantity(Product product, int quantity) {
        products.put(product, quantity);
    }

    public int getQuantity(Product product) {
        return products.getOrDefault(product, 0);
    }

    public boolean isAvailable(Product product) {
        return products.containsKey(product) && products.get(product) > 0;
    }
}

interface State {
    void selectProduct(Product product);
    void insertCoin(Coin coin);
    void insertNote(Note note);
    void dispenseProduct();
    void returnChange();
}


class IdleState implements State {
    private final VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
        if (vendingMachine.inventory.isAvailable(product)) {
             vendingMachine.setSelectedProduct(product);
             vendingMachine.setState(new ReadyState(vendingMachine));
            System.out.println("Product selected: " + product.getName());
        } else {
            System.out.println("Product not available: " + product.getName());
        }
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Please select a product first");
    }

    @Override
    public void insertNote(Note note) {
        System.out.println("Please select a product first");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please select a product and make payment first");
    }

    @Override
    public void returnChange() {
        System.out.println("No change to return");
    }
}
class ReadyState implements State {
    private final VendingMachine vendingMachine;

    public ReadyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Product already selected. Please make Payment");
    }

    @Override
    public void insertCoin(Coin coin) {
        vendingMachine.addCoin(coin);
        System.out.println("Coin inserted: " + coin);
        checkPaymentStatus();
    }

    @Override
    public void insertNote(Note note) {
        vendingMachine.addNote(note);
        System.out.println("Note inserted: " + note);
        checkPaymentStatus();
    }


    @Override
    public void dispenseProduct() {
        System.out.println("Please make payment first.");
    }

    @Override
    public void returnChange() {
        System.out.println("Please make payment first.");
    }

    private void checkPaymentStatus() {
        if (vendingMachine.getTotalPayment() >= vendingMachine.getSelectedProduct().getPrice()) {
            vendingMachine.setState(new DispenseState(vendingMachine));
        }
    }
}
class DispenseState implements State {
    private final VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Product already selected. Please collect the dispensed product.");
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Payment already made. Please collect the dispensed product.");
    }

    @Override
    public void insertNote(Note note) {
        System.out.println("Payment already made. Please collect the dispensed product.");
    }

    @Override
    public void dispenseProduct() {
        vendingMachine.setState(new ReadyState(vendingMachine));

        Product product = vendingMachine.getSelectedProduct();
        vendingMachine.inventory.updateQuantity(product, vendingMachine.inventory.getQuantity(product) - 1);
        System.out.println("Product dispensed: " + product.getName());
        vendingMachine.setState(new ReturnChangeState(vendingMachine));
    }

    @Override
    public void returnChange() {
        System.out.println("Please collect the dispensed product first.");
    }
}
class ReturnChangeState implements State {
    private final VendingMachine vendingMachine;

    public ReturnChangeState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Please collect the change first.");
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Please collect the change first.");
    }

    @Override
    public void insertNote(Note note) {
        System.out.println("Please collect the change first.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Product already dispensed. Please collect the change.");
    }

    @Override
    public void returnChange() {
        double change = vendingMachine.getTotalPayment() - vendingMachine.getSelectedProduct().getPrice();
        if (change > 0) {
            System.out.println("Change returned: $"+change);
        } else {
            System.out.println("No change to return");
        }

        vendingMachine.resetPayment();
        vendingMachine.resetSelectedProduct();
        vendingMachine.setState(new IdleState(vendingMachine));
    }
}


class VendingMachine {
    private static VendingMachine instance;
    Inventory inventory;
    private State currentState;

    @Getter
    @Setter
    private Product selectedProduct;
    private double totalPayment;

    private VendingMachine() {
        inventory = new Inventory();
        currentState = new IdleState(this);
        selectedProduct = null;
        totalPayment = 0;
    }

    public static synchronized VendingMachine getInstance() {
        if (instance == null) {
            instance = new VendingMachine();
        }

        return instance;
    }

    public void selectProduct(Product product) {
        currentState.selectProduct(product);
    }

    public void insertCoin(Coin coin) {
        currentState.insertCoin(coin);
    }

    public void insertNote(Note note) {
        currentState.insertNote(note);
    }

    public void dispenseProduct() {
        currentState.dispenseProduct();
    }

    public void returnChange() {
        currentState.returnChange();
    }

    void setState(State state) {
        currentState = state;
    }

    void resetSelectedProduct() {
        selectedProduct = null;
    }

    double getTotalPayment() {
        return totalPayment;
    }

    void addCoin(Coin coin) {
        totalPayment += coin.getValue();
    }

    void addNote(Note note) {
        totalPayment += note.getValue();
    }

    void resetPayment() {
        totalPayment = 0;
    }
}

public class Runner {
    public static void main(String[] args) {
        VendingMachine vendingMachine = VendingMachine.getInstance();

        // Add products to the inventory
        Product coke = new Product("Coke", 1.5);
        Product pepsi = new Product("Pepsi", 1.5);
        Product water = new Product("Water", 1.0);

        vendingMachine.inventory.addProduct(coke, 5);
        vendingMachine.inventory.addProduct(pepsi, 3);
        vendingMachine.inventory.addProduct(water, 2);


        // Select a product
        vendingMachine.selectProduct(coke);
        vendingMachine.insertNote(Note.TWENTY);
        // Insert coins
        vendingMachine.dispenseProduct();
        vendingMachine.returnChange();


        // Select another product
        vendingMachine.selectProduct(pepsi);

        // Insert insufficient payment
        vendingMachine.insertCoin(Coin.QUARTER);

        // Try to dispense the product
        vendingMachine.dispenseProduct();

        // Insert more coins
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.QUARTER);

        // Dispense the product
        vendingMachine.dispenseProduct();

        // Return change
        vendingMachine.returnChange();
    }
}
