package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Order implements Serializable {
    private String orderReference;
    private String orderDetails;
    private User owner;
//    HashMap<orderedInventoryItem, orderedQuantity>
    private HashMap<InventoryItem, Integer> orderedItems;
    private double totalPrice;

    public Order(User owner, String orderDetails) {
        this.owner = owner;
        this.orderDetails = orderDetails;
        this.orderReference = this.owner.getUsername() + " --> " + this.orderDetails;
        this.orderedItems = new HashMap<>();
        this.totalPrice = 0.0;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public HashMap<InventoryItem, Integer> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(HashMap<InventoryItem, Integer> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public void addItem(InventoryItem inventoryItem, int quantity) {
        this.orderedItems.put(inventoryItem, quantity);
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getOrderReference() {
        return orderReference;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double calcTotalPrice() {
        this.orderedItems.keySet().forEach(item -> this.totalPrice += item.getPrice() * this.orderedItems.get(item));
        return this.totalPrice;
    }

    public void view() {
        System.out.println("Order reference: " + this.orderReference);
        System.out.println("Order owner: " + this.owner.getName());
        System.out.println("Owner phone number: " + this.owner.getPhoneNumber());
        System.out.println("Order items: ");
        this.getOrderedItems()
                .keySet()
                .forEach(item -> {
                    System.out.println("Id: " + item.getId() + " | item: " +
                    item.getDetails() + " | ordered quantity " +
                    this.getOrderedItems().get(item) + " | price: " +
                    item.getPrice());
        });
        System.out.println("Total price: " + this.calcTotalPrice());
    }

}
