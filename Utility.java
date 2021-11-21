/**
 * @author Yash Kapoor
 * @version Milestone 3
 *
 * Class representing the different utilities available in Monopoly.
 * There are only two utilities.
 *
 * If one utility is owned, then the rent is 4 times the amount shown on the dice.
 * If two utilities are owned, then the rent is 10 times the amount shown on the dice.
 */
public class Utility extends Property {

    /**
     * @author Yash Kapoor
     * Instantiates a utility.
     *
     * @param name      a String, the name of the utility
     * @param cost      an int, the cost of the utility
     */
    public Utility(String name, int cost) {
        super(name, cost);

    }

    /**
     * @author Yash Kapoor
     * Calculates the rent that the player needs to pay to the owner of the utility
     * depending on the number of utilities owned by the owner and the total dice roll of the player
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

        int ownedUtilities = 0;

        // iterating through the owner's owned properties
        for (Property property : owner.getProperties()) {
            if (property instanceof Utility) {
                ownedUtilities++;
            }
        }

        if (ownedUtilities == 1) {
            // 1 utility owned -> rent is 4 times the amount shown on dice
            rent = player.getTotalRoll() * 4;
        }
        else {
            // Otherwise, 2 utilities owned -> rent is 10 times the amount shown on dice
            rent = player.getTotalRoll() * 10;
        }

        return rent;

    }

    /**
     * @author Yash Kapoor
     *
     * Shows the user a text representation of what utility they are currently on.
     *
     * @return      a String, the name of the square
     */
    @Override
    public String toString() {
        return String.format("%s", getName());
    }
}
