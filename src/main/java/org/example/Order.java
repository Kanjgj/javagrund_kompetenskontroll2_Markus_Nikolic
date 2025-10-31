package org.example;

import java.util.List;

public class Order {
    private final int orderId;
    private final String customerName;
    private final List<Product> products;

    public Order(int orderId, String customerName, List<Product> products) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.products = products;
    }

    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public List<Product> getProducts() { return products; }

    @Override
    public String toString() {
        return "Order " + orderId + " av " + customerName + " (" + products.size() + " produkter)";
    }
}
