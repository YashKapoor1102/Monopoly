public class Property {

    private String propertyName;
    private int cost;
    private Player owner;


    public Property(String pName, int c) {
        this.propertyName = pName;
        this.cost = c;
        this.owner = null;

    }

    public String getName() {
        return propertyName;
    }

    public Player getOwner() {
        return owner;
    }

    public int getCost() {
        return cost;
    }


}
