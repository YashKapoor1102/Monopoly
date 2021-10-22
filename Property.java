/**
 * The type Property.
 */
public class Property {

    private String propertyName;
    private int cost;
    private Player owner;


    /**
     * Instantiates a new Property.
     *
     * @param pName the p name
     * @param c     the c
     */
    public Property(String pName, int c) {
        this.propertyName = pName;
        this.cost = c;
        this.owner = null;

    }

    /**
     * Returns the name of the property.
     *
     * @return the name
     */
    public String getName() {
        return propertyName;
    }

    /**
     * Returns owner (Player) of property.
     *
     * @return the owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Returns the cost of the property.
     *
     * @return the cost
     */
    public int getCost() {
        return cost;
    }


}
