package Model;

public class InventoryItem extends AbstractItem{
    public int id;
    public int quantity;
    public String description;
    public boolean isReadyForSell;

    public double price;

    public InventoryItem(int id, int quantity, String description) {
        this.id = id;
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
        return null;
    }

    @Override
    public void calcValue() {

    }

    @Override
    public String getDescription() {
        return null;
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

}
