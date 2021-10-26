/**
 * @author Himanshu Singh
 * @version Milestone 1
 */

import java.util.*;

/**
 * Player Class that has information about a typical player
 * in the game of Monopoly.
 *
 * NOTE: We are aware that money is declared as an integer value and not a double value.
 * We did this intentionally because no change is given in monopoly! However, if you want us to change it
 * into a double value, we can for the next milestone.
 */
public class Player {
    private int money;
    private final String name;
    private final List<Property> PROPERTIES;
    private int position;

    /**
     * Instantiates a new Player.
     *
     * @param name  the name
     * @param money the money
     */
    public Player(String name, int money){

        this.PROPERTIES = new ArrayList<>();
        this.name = name;
        this.money = money;
    }

    /**
     * Removes money from player's total amount.
     *
     * @param money     an int, the amount of money to be removed
     */
    public void removeMoney(int money){
        this.money = this.money - money;
    }

    /**
     * Adds money to player's total amount.
     *
     * @param money     an int, the amount of money to be added
     */
    public void addMoney(int money){
        this.money = this.money + money;
    }

    /**
     * Gets PROPERTIES that the player owns
     *
     * @return      a List, the PROPERTIES
     */
    public List<Property> getProperties() {
        return this.PROPERTIES;
    }

    /**
     * Returns the amount of money a player has.
     *
     * @return      an int, the amount of money
     */
    public int getMoney(){
        return this.money;
    }

    /**
     * Returns the player's name.
     *
     * @return the string
     */
    public String getName() {
        return this.name;
    }

    /**
     * Adds a property to a player's list of PROPERTIES.
     *
     * @param property      a Property Object, the property that needs to be added
     */
    public int addProperty(Property property) {

        if(PROPERTIES.toString().contains(property.toString())) {
            return -1;
        }

        this.PROPERTIES.add(property);

        return 0;
    }

    /**
     * Getting the list of positions
     *
     * @return          a List, the list of positions that the player has moved to on the gameboard
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * @author Robert Simionescu
     * @param position The player's new position as an int.
     */
    public void setPosition(int position)
    {
        this.position = position;
    }

}
