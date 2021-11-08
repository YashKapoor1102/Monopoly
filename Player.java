/**
 * @author Himanshu Singh
 * @version Milestone 2
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
    private boolean bankrupt;

    /**
     * @author Himanshu Singh
     * Instantiates a new Player.
     *
     * @param name  the name
     * @param money the money
     */
    public Player(String name, int money){

        this.PROPERTIES = new ArrayList<>();
        this.name = name;
        this.money = money;
        this.bankrupt = false;
    }

    /**
     * @author Himanshu Singh
     * Removes money from player's total amount.
     *
     * @param money     an int, the amount of money to be removed
     */
    public void removeMoney(int money){
        this.money = this.money - money;
    }

    /**
     * @author Robert Simionescu
     * @return True if the player is bankrupt, false otherwise.
     */
    public boolean isBankrupt() {
        return bankrupt;
    }

    /**
     * Setter for the bankruptcy state of a player.
     * @param bankruptcyState True if the player is bankrupt, false if they are not.
     */
    public void setBankruptcy(boolean bankruptcyState)
    {
        this.bankrupt = bankruptcyState;
    }

    /**
     * @author Himanshu Singh
     * Adds money to player's total amount.
     *
     * @param money     an int, the amount of money to be added
     */
    public void addMoney(int money){
        this.money = this.money + money;
    }

    /**
     * @author Himanshu Singh
     * Gets PROPERTIES that the player owns
     *
     * @return      a List, the PROPERTIES
     */
    public List<Property> getProperties() {
        return this.PROPERTIES;
    }

    /**
     * @author Himanshu Singh
     * Returns the amount of money a player has.
     *
     * @return      an int, the amount of money
     */
    public int getMoney(){
        return this.money;
    }

    /**
     * @author Himanshu Singh
     * Returns the player's name.
     *
     * @return the string
     */
    public String getName() {
        return this.name;
    }

    /**
     * @author Himanshu Singh
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
     * @author Himanshu Singh
     * Getting the player's position
     *
     * @return          int representing the player's position on the board
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
