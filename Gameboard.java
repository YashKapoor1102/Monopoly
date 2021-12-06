/**
 * @author Robert Simionescu
 * @version Milestone 4
 */

import java.io.Serializable;
import java.util.*;

/**
 * @author Robert Simionescu
 * Class representing the Monopoly gameboard. The gameboard consists of a number of squares in a fixed order. When
 * players reach the end of the board, they return to the start and continue.
 */
public class Gameboard implements Serializable
{
    /**
     * @author Robert Simionescu
     * ArrayList containing all the squares on the board in the order players travel through them.
     */
    private ArrayList<Square> squares;

    /**
     * @author Robert Simionescu
     * Initializes a Gameboard with the list of squares that are passed.
     * @param squares       an ArrayList of Squares in the order players will travel through them.
     */
    public Gameboard(ArrayList<Square> squares)
    {
        this.squares = squares;
    }

    /**
     * @author Robert Simionescu
     * @return      an ArrayList of all squares on the gameboard.
     */
    public ArrayList<Square> getSquares() {
        return squares;
    }

    /**
     * @author Robert Simionescu
     * Returns the square found at a certain distance from the starting point.
     * @param position The number of squares past the starting point that the desired square is found.
     * @return          a Square Object, the position of that specific square on the gameboard
     */
    public Square getSquare(int position)
    {
        return this.squares.get(position);
    }

    /**
     * @author Robert Simionescu
     * Returns the position of the square with the specified name. Strings should never be passed directly into this.
     * Instead, use the appropriate LocalizationHandler method for the desired square to ensure it works regardless
     * of the language.
     * @param squareName The name of the square whose position should be returned.
     * @return The position of the square with the specified name.
     */
    public int getPosition(String squareName)
    {
        for (int i = 0; i < squares.size(); i++)
        {
            if (squares.get(i).getName().equals(squareName))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * @author Robert Simionescu
     * Get the position of the "Jail" square on the gameboard
     * @return      an int, the position of the "Jail" square
     */
    public int getJail()
    {
        return getPosition(LocalizationHandler.getJailName());
    }

    /**
     * @author Robert Simionescu
     * Get the position of the "Go to Jail" square on the gameboard
     * @return      an int, the position of the "Go to Jail" square
     */
    public int getGoToJail()
    {
        return getPosition(LocalizationHandler.getGoToJailName());
    }


}
