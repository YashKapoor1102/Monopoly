import java.util.ArrayList;

/**
 * @author Robert Simionescu
 * Class representing the Monopoly gameboard. The gameboard consists of a number of squares in a fixed order. When
 * players reach the end of the board, they return to the start and continue.
 */
public class Gameboard
{
    /**
     * I don't think any of this fits with the way we made our UML diagram, but just in case I haven't deleted it
     * entirely.
     */

//    private String[] properties;
//    private int[] prices;
//
//    public Gameboard(){
//        this.properties = new String[]{"Mediterranean Avenue/Old Kent Rd", "Baltic Avenue/Whitechapel Rd",
//                "Oriental Avenue/The Angel, Islington", "Vermont Avenue/Euston Rd",
//                "Connecticut Avenue/Pentonville Rd", "St. Charles Place/Pall Mall", "States Avenue/Whitehall",
//                "Virginia Avenue/Northumberland Ave", "St. James Place/Bow St", "Tennessee Avenue/Marlborough St",
//                "New York Avenue/Vine St", "Kentucky Avenue/Strand", "Indiana Avenue/Fleet St", "Illinois Avenue/Trafalgar Sq",
//                "Atlantic Avenue/Leicester Sq", "Ventnor Avenue/Coventry St", "* Marvin Gardens [Marven Gardens]/Piccadilly",
//                "Pacific Avenue/Regent St", "* North Carolina Avenue [South Carolina Avenue]/Oxford St",
//                "Pennsylvania Avenue/Bond St", "Park Place/Park Lane", "Boardwalk/Mayfair"};
//        this.prices = new int[]{60, 60, 100, 100, 120, 140, 140, 160, 180, 180, 200, 220, 220, 240, 260, 260, 280,
//                300, 300, 320, 350, 400};
//
//    }

    /**
     * @author Robert Simionescu
     * ArrayList containing all the squares on the board in the order players travel through them.
     */
    private ArrayList<Square> squares;

    /**
     * @author Robert Simionescu
     * Initializes a Gameboard with the list of squares that are passed.
     * @param squares An ArrayList of Squares in the order players will travel through them.
     */
    public Gameboard(ArrayList<Square> squares)
    {
        this.squares = squares;
    }

    /**
     * @author Robert Simionescu
     * Returns an ArrayList of all squares on the gameboard.
     */
    public ArrayList<Square> getSquares() {
        return squares;
    }

    /**
     * Returns the square found at a certain distance from the starting point.
     * @param position The number of squares past the starting point that the desired square is found.
     * @return
     */
    public Square getSquare(int position)
    {
        return this.squares.get(position);
    }

}
