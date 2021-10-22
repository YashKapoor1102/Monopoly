import java.util.ArrayList;

/**
 * @author Robert Simionescu
 * Class representing the different streets available in Monopoly. In addition to the name and cost of all properties,
 * Streets have a colour that is used to determine rent costs and ability to build houses. If a player owns all streets
 * of the same colour, the rent of all of those streets doubles from 10% of the cost to 20%. In future milestones, once
 * players reach this point they may build houses and hotels on their streets.
 */
public class Street extends Property
{
    /**
     * The colour of the street as a String.
     */
    private String colour;

    /**
     * The gameboard on which this street is situated. Needed to calculate the number of tiles of a matching colour,
     * which is used to calculate rent.
     */
    private Gameboard gameboard;

    /**
     * @author Robert Simionescu
     * Instantiates a Street. Takes a street name, colour, and the initial cost.
     * @param name The name of the street as a string.
     * @param colour The colour of the street as a string.
     * @param cost The cost of the street as an int.
     */
    Street(String name, String colour, int cost)
    {
        super(name, cost);
        this.colour = colour;
    }

    /**
     * @author Robert Simionescu
     * Returns the colour of the street.
     * @return The colour of the street as a String.
     */
    public String getColour()
    {
        return this.colour;
    }

    /**
     * Calculates and returns the rent for players landing on this street. Rent begins at 10% of the cost of the street
     * and goes up to 20% if the owner of the street owns all of the streets with this colour.
     * @return The rent of the street rounded down as an int.
    */
    public int calculateRent()
    {
        double rent = this.getCost() * 0.1;

        // Iterates over all owned properties and counts how many are streets of the same colour as this one.
        ArrayList<Property> ownerProperties = this.getOwner().getProperties();
        int ownedSquaresMatchingColour = 0;

        for (Property property : ownerProperties)
        {
            if (property instanceof Street)
            {
                if (((Street) property).getColour().equals(this.colour))
                {
                    ownedSquaresMatchingColour++;
                }
            }
        }

        // Iterates over all squares on the gameboard and counts how many are streets of the same colour as this one.
        ArrayList<Square> gameboardSquares = this.gameboard.getSquares();
        int totalSquaresMatchingColour = 0;

        for (Square square : gameboardSquares)
        {
            if (square instanceof Street)
            {
                if (((Street) square).getColour().equals(this.colour))
                {
                    totalSquaresMatchingColour++;
                }
            }
        }

        // If the owner of this square does not own all of the streets of this colour, the rent is 10% of the cost.
        if (ownedSquaresMatchingColour != totalSquaresMatchingColour)
        {
            return (int) rent;
        }

        // If the owner of the square owns all streets of this colour, the rent is 20% of the cost. Future milestones
        // will add houses and hotels to be accounted for after this.
        return (int) (rent * 2);
    }


}
