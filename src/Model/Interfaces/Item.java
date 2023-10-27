package Model.Interfaces;

import java.io.Serializable;

public interface Item extends Serializable {
    public String getDetails();
    public void calcValue();
    public String getDescription();

}
