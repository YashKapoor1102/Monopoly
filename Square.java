/**
 * @author Robert Simionescu and Yash Kapoor
 * @version Milestone 4
 *
 */

import java.io.Serializable;

/**
 * @author Robert Simionescu and Yash Kapoor
 *
 * Interface representing a square on the gameboard. Used primarily to allow Gameboard to have a list of all squares and
 * have them share a type. There are a number of different types of squares in the game, most of which have not been
 * implemented in this milestone. This interface exists mostly to make expanding the types of squares in later
 * milestones easier.
 */
public interface Square extends Serializable {
    /**
     * @author Robert Simionescu
     * Returns the name of the square.
     * @return The name of the square as a String.
     */
    String getName();

    /**
     * @author Yash Kapoor
     * Shows the user a text representation of what square they are currently on.
     *
     * @return      a String, the name of the square
     */
    String toString();
}
