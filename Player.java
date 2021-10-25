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
    private final List<Integer> POSITION;

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
        this.POSITION = new ArrayList<>();
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
     * The list of positions in order that the player moves to on the gameboard
     * reported as an integer. For example, if there are 23 squares and the player
     * moves 5 spaces, the player is on position 4 (starting at index 0) or square #5.
     *
     * @param position      an int, the current position of the player on the gameboard
     */
    public void addPosition(int position) {
        this.POSITION.add(position);

    }

    /**
     * Getting the list of positions
     *
     * @return          a List, the list of positions that the player has moved to on the gameboard
     */
    public List<Integer> getPosition() {
        return this.POSITION;
    }

}
