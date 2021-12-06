/**
 * @author Robert Simionescu and Yash Kapoor
 * @version Milestone 4
 *
 * Represents all generic squares on the gameboard
 * (i.e., all squares that are not streets, utilities,
 * and railroads. In these simplified versions of Monopoly,
 * they are "Go to Jail", "Jail", and "Go")
 */
public class GenericSquare implements Square
{
    private final String NAME;

    /**
     * @author Robert Simionescu
     *
     * Constructor that initializes the generic square
     *
     * @param name      a String, the name of the generic square
     */
    public GenericSquare(String name)
    {
        this.NAME = name;
    }

    /**
     * @author Robert Simionescu
     *
     * Get the name of the specific generic square
     *
     * @return     a String, the name of the generic square
     */
    @Override
    public String getName() {
        return this.NAME;
    }

    /**
     * @author Yash Kapoor
     *
     * Shows the user a text representation of what generic square they are currently on.
     *
     * @return     a String, the name of the square
     */
    @Override
    public String toString() {
        return String.format("%s", getName());
    }


}
