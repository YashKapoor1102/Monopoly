/**
 * @author Himanshu Singh, Yash Kapoor, and Robert Simionescu
 * @version Milestone 3
 */

import java.util.*;

/**
 * Player Class that has information about a typical player
 * in the game of Monopoly.
 *
 */
public class Player {

    private int money;
    private final String name;
    private final List<Property> PROPERTIES;
    private int position;
    private boolean bankrupt;
    private int doubleCount;

    private boolean inJail;

    private int totalRoll;

    private int totalNumberHouses;
    private int totalNumberHotels;

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
        this.doubleCount = 0;

        this.inJail = false;

        this.totalNumberHouses = 0;
        this.totalNumberHotels = 0;
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
        return this.bankrupt;
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
     * Setting the player's position
     *
     * @param position The player's new position as an int.
     */
    public void setPosition(int position)
    {
        this.position = position;
    }

    /**
     * @author Yash Kapoor
     *
     * Get the number of times the player rolls doubles in a row
     * @return      an int, number of times the player rolls doubles
     */
    public int getDoubleCount() {
        return this.doubleCount;
    }

    /**
     * @author Yash Kapoor
     *
     * Set the number of times the player rolls doubles in a row
     * @param dc   an int, number of times the player rolls doubles
     */
    public void setDoubleCount(int dc) {
        this.doubleCount = dc;
    }

    /**
     * @author Yash Kapoor
     *
     * Set the player's jail status
     * @param jail      a boolean, true if the player is in jail, false otherwise
     */
    public void setInJail(boolean jail) {
        this.inJail = jail;
    }

    /**
     * @author Yash Kapoor
     *
     * Get the player's jail status
     * @return          a boolean, true if the player is in jail, false otherwise
     */
    public boolean getInJail() {
        return this.inJail;
    }

    /**
     * @author Yash Kapoor
     *
     * Set the amount the player rolled
     * @param totalRoll     an int, first and second roll added together
     */
    public void setTotalRoll(int totalRoll) {
        this.totalRoll = totalRoll;
    }

    /**
     * @author Yash Kapoor
     *
     * Get the amount the player rolled
     * @return      an int, first and second roll added together
     */
    public int getTotalRoll() {
        return this.totalRoll;
    }

    /**
     *
     * @return
     */
    public int getTotalHouses() {
        return this.totalNumberHouses;
    }

    /**
     *
     * @param totalHouses
     */
    public void setTotalNumberHouses(int totalHouses) {
        this.totalNumberHouses = totalHouses;
    }

    /**
     *
     * @return
     */
    public int getTotalNumberHotels() {
        return this.totalNumberHotels;
    }

    /**
     *
     * @param totalHotels
     */
    public void setTotalNumberHotels(int totalHotels) {
        this.totalNumberHotels = totalHotels;
    }

}
