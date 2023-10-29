package Model;

import Model.Interfaces.Categorizable;
import Model.Interfaces.Perishable;

public class GroceryItem extends InventoryItem implements Categorizable, Perishable {
    private boolean isFresh;
    private String category;
    private boolean isExpired;

    public GroceryItem(int id, int quantity, String details, String description, boolean fresh, String category) {
        super(id, quantity, details, description);
        this.isFresh = fresh;
        this.category = category;
        this.isExpired = false;
    }

    @Override
    public void calcValue() {
        if(this.isFresh) {
            System.out.println("Fresh products are more expensive than packed ones");
        } else {
            System.out.println("Unfresh products are cheaper but not very healthy");
        }
    }

    public boolean isFresh() {
        return this.isFresh;
    }

    public void setFresh(boolean fresh) {
        this.isFresh = fresh;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean isExpired() {
        return this.isExpired;
    }

    @Override
    public void setExpired() {
        this.isExpired = true;
    }

    @Override
    public void handleExpiration() {

    }

}
