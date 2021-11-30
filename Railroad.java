import java.io.Serializable;

/**
 * @author Yash Kapoor
 * @version Milestone 3
 *
 * Class representing the different railroads available in Monopoly.
 * If a player owns 1 railroad, rent is $25.
 * If a player owns 2 railroads, rent is $50.
 * If a player owns 3 railroads, rent is $100.
 * If a player owns 4 railroads, rent is $200.
 */
public class Railroad extends Property implements Serializable {

    /**
     * @author Yash Kapoor
     * Instantiates a railroad
     *
     * @param name      a String, the name of the railroad
     * @param cost      an int, the cost of the railroad
     */
    public Railroad(String name, int cost) {
        super(name, cost);
    }

    /**
     * @author Yash Kapoor
     * Calculates the rent that the player needs to pay to the owner of the railroad
     * depending on how many railroads the owner owns
     *
     * @param player    a Player object, the player who landed on the owner's property
     * @param gameboard The gameboard on which the square is found. Rent in properties depends on which other properties
     *                  are owned by the same player, so this is necessary.
     *
     * @return          an int, the rent that must be paid to the owner of the property
     */
    @Override
    public int calculateRent(Player player, Gameboard gameboard) {
        Player owner = this.getOwner();
        int rent;

        // Iterates over all owned properties and counts how many are streets of the same colour as this one.
        int ownedRailroads = 0;

        // iterating through the owner's owned properties
        for (Property property : owner.getProperties()) {
            if (property instanceof Railroad) {
                ownedRailroads++;
            }
        }

        if (ownedRailroads == 1) {
            rent = 25;
            return rent;
        }
        else if (ownedRailroads == 2) {
            rent = 50;
            return rent;
        }
        else if (ownedRailroads == 3) {
            rent = 100;
            return rent;
        }
        else {
            rent = 200;
            return rent;
        }

    }

    /**
     * @author Yash Kapoor
     *
     * Shows the user a text representation of what railroad they are currently on.
     *
     * @return      a String, the name of the square
     */
    @Override
    public String toString() {
        return String.format("%s", getName());
    }
}
