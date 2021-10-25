/**
 * @author Robert Simionescu and Yash Kapoor
 * @version Milestone 1
 *
 * Interface representing a square on the gameboard. Used primarily to allow Gameboard to have a list of all squares and
 * have them share a type. There are a number of different types of squares in the game, most of which have not been
 * implemented in this milestone. This interface exists mostly to make expanding the types of squares in later
 * milestones easier.
 */
public interface Square
{
    /**
     * @author Robert Simionescu
     * Returns the name of the square.
     * @return The name of the square as a String.
     */
    public String getName();

    /**
     * @author Yash Kapoor
     *
     * Setting the property that exists on the square (if any)
     * Null if there is no property on that specific square.
     *
     * This may be modified slightly in later milestones when we have to account for more than
     * one type of property on the gameboard.
     *
     * @return      a Street Object, the street that is present on the square
     */
    public Street setProperty();

    /**
     * @author Yash Kapoor
     * Shows the user a text representation of what square they are currently on.
     *
     * @return      a String, the name of the square
     */
    public String toString();
}
