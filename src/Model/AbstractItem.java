package Model;

import Model.Interfaces.*;

public abstract class AbstractItem implements Item, Sellable {
    @Override
    public abstract String getDetails();

    @Override
    public abstract void calcValue();

    @Override
    public abstract String getDescription();

}
