package Model;

import Model.Interfaces.Breakable;

public class FragileItem extends InventoryItem implements Breakable {
    private String material;
    private double weight;
    private boolean isBroken;

    public FragileItem(int id, int quantity, String details, String description, String material, double weight) {
        super(id, quantity, details, description);
        this.material = material;
        this.weight = weight;
        this.isBroken = false;
    }


    @Override
    public void calcValue() {
        if(this.material.equals("glass")) {
            System.out.println("This item must be expensive");
        } else {
            System.out.println("It is not very likely to be expensive");
        }
    }

    @Override
    public boolean isBroken() {
        return this.isBroken;
    }

    @Override
    public void handleBreakage() {

    }

}
