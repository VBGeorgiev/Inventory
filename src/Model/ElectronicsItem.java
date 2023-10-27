package Model;

import Model.Interfaces.Categorizable;

public class ElectronicsItem extends InventoryItem implements Categorizable {
    private int voltage;
    private String category;

    public ElectronicsItem(int id, int quantity, String description, int voltage, String category) {
        super(id, quantity, description);
        this.voltage = voltage;
        this.category = category;
    }

    @Override
    public void calcValue() {
        if(this.voltage >= 220) {
            System.out.println("Item value is probably high");
        } else {
            System.out.println("Item value should not be very high");
        }
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }
}
