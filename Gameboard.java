/**
 * @author Robert Simionescu
 * @version Milestone 1
 */

import java.util.*;

/**
 * @author Robert Simionescu
 * Class representing the Monopoly gameboard. The gameboard consists of a number of squares in a fixed order. When
 * players reach the end of the board, they return to the start and continue.
 */
public class Gameboard
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


}
