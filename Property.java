import java.io.Serializable;

/**
 * @author Himanshu Singh and Robert Simionescu
 * @version Milestone 3
 *
 * An abstract Property Class that implements the
 * Square Interface.
 *
 * This has information about a typical property in
 * Monopoly.
 */
public abstract class Property implements Square, Serializable {

    private final String PROPERTY_NAME;
    private final int COST;
    private Player owner;

    /**
     * @author Himanshu Singh
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
     * @author Himanshu Singh
     * Gets the name of the property.
     *
     * @return      a String, the name of the property
     */
    public String getName() {
        return PROPERTY_NAME;
    }

    /**
     * @author Himanshu Singh
     * Sets owner (Player) of property.
     *
     * @param player    a Player Object, the player who is going to own the property
     */
    public void setOwner(Player player) {
        this.owner = player;
    }

    /**
     * @author Himanshu Singh
     * Gets owner (Player) of property.
     *
     * @return      a Player Object, the player who owns the property
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * @author Himanshu Singh
     * Returns the COST of the property.
     *
     * @return the COST
     */
    public int getCost() {
        return COST;
    }

    /**
     * @author Robert Simionescu
     * This method will be used to calculate the rent of a particular property. Implementation depends on the type of
     * property.
     * @param player    a Player object, the player who landed on the owner's property
     * @param gameboard The gameboard on which the square is found. Rent in properties depends on which other properties
     *                  are owned by the same player, so this is necessary.
     *
     * @return int containing the rent.
     */
    abstract int calculateRent(Player player, Gameboard gameboard);

}
