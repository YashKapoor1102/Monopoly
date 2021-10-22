import java.util.ArrayList;

/**
 * The type Player.
 */
public class Player {
    private int money;
    private final String name;
    private ArrayList<Property> properties;

    /**
     * Instantiates a new Player.
     *
     * @param name  the name
     * @param money the money
     */
    public Player(String name, int money){
        this.name = name;
        this.money = money;
    }

    /**
     * Removes money from player's total amount.
     *
     * @param money the money
     */
    public void removeMoney(int money){
        this.money = this.money - money;
    }

    /**
     * Adds money to player's total amount.
     *
     * @param money the money
     */
    public void addMoney(int money){
        this.money = this.money + money;
    }

    /**
     * Gets properties that the player owns
     *
     * @return the properties
     */
    public ArrayList<Property> getProperties() {
        return this.properties;
    }


    /**
     * Returns the amount of money a player has.
     *
     * @return the int
     */
    public int getMoney(){
        return this.money;
    }

    /**
     * Returns the player's name.
     *
     * @return the string
     */
    public String getName(){
        return this.name;
    }

    /**
     * Add's a property to a player's list of properties.
     *
     * @param property the property
     */
    public void addProperty(Property property){
        this.properties.add(property);
    }

}
