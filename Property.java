/**
 * @author Himanshu Singh
 * @version Milestone 1
 */

/**
 * An abstract Property Class that implements the
 * Square Interface.
 *
 * This has information about a typical property in
 * Monopoly.
 */
public abstract class Property implements Square {

    private final String PROPERTY_NAME;
    private final int COST;
    private Player owner;

    /**
     * Instantiates a new Property.
     *
     * @param pName     a String, the property's name
     * @param c         an int, the cost of the property
     */
    public Property(String pName, int c) {
        this.PROPERTY_NAME = pName;
        this.COST = c;
        this.owner = null;

    }

    /**
     * Gets the name of the property.
     *
     * @return      a String, the name of the property
     */
    public String getName() {
        return PROPERTY_NAME;
    }

    /**
     * Sets owner (Player) of property.
     *
     * @param player    a Player Object, the player who is going to own the property
     */
    public void setOwner(Player player) {
        this.owner = player;
    }

    /**
     * Gets owner (Player) of property.
     *
     * @return      a Player Object, the player who owns the property
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Returns the COST of the property.
     *
     * @return the COST
     */
    public int getCost() {
        return COST;
    }

    abstract int calculateRent(Gameboard gameboard);

}
