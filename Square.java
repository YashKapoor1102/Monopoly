import java.util.ArrayList;

/**
 * @author Robert Simionescu
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
    String getName();

    /**
     * @author Robert Simionescu
     * Returns an ArrayList of all players on the square.
     * @return An ArrayList of Players containing all players on the square.
     */
    ArrayList<Player> getPlayers();
}
