package Model;

public class InventoryItem extends AbstractItem{
    public int id;
    public String details;
    public int quantity;
    public String description;
    public boolean isReadyForSell;

    public double price;

    public InventoryItem(int id, int quantity, String details, String description) {
        this.id = id;
        this.details = details;
        this.quantity = quantity;
        this.description = description;
        this.isReadyForSell = false;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void calcValue() {

    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean isReadyForSell() {
        return this.isReadyForSell;
    }

    @Override
    public void setSellable(double price) {
        this.price = price;
        this.isReadyForSell = true;
    }

    public double getPrice() {
        return this.price;
    }

    public void view() {
        System.out.println("==========================================");
        System.out.println("Id: " + this.getId() + " | Quantity: " + this.getQuantity());
        System.out.println("Item details: " + this.getDetails());
        System.out.println("Item description: " + this.getDescription());
        if (this.isReadyForSell()) {
            System.out.println("This item is ready for sale and its price is " + this.getPrice() + " лв");
            System.out.println("==========================================");
        } else {
            System.out.println("This item is not ready for sale");
            System.out.println("==========================================");
        }

    }

}
