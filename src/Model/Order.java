package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private String orderReference;
    private String orderDetails;
    private User owner;
    private ArrayList<InventoryItem> orderedItems;
    private double totalPrice;

    public Order(User owner, String orderDetails) {
        this.owner = owner;
        this.orderDetails = orderDetails;
        this.orderReference = this.owner.getUsername() + "--" + this.orderDetails;
        this.orderedItems = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public ArrayList<InventoryItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(ArrayList<InventoryItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public void addItem(InventoryItem inventoryItem) {
        this.orderedItems.add(inventoryItem);
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

    public double calcTotalPrice() {
        this.orderedItems.forEach(item -> this.totalPrice += item.getPrice());
        return this.totalPrice;
    }

}
