package Model.Interfaces;

public interface Perishable {
    public boolean isExpired();
    public void setExpired();
    public void handleExpiration();
}
