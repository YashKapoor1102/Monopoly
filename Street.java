/**
 * @author Robert Simionescu and Yash Kapoor
 * @version Milestone 3
 */
import java.io.Serializable;
import java.util.*;

/**
 * @author Robert Simionescu
 * Class representing the different streets available in Monopoly. In addition to the name and cost of all properties,
 * Streets have a colour that is used to determine rent costs and ability to build houses. If a player owns all streets
 * of the same colour, the rent of all of those streets doubles from 10% of the cost to 20%. In future milestones, once
 * players reach this point they may build houses and hotels on their streets.
 */
public class Street extends Property implements Serializable
{
    /**
     * The colour of the street as a String.
     */
    private String colour;

    private int houseCost;
    private int hotelCost;

    private int houses;
    private int hotel;

    private boolean maxCapacityReached;

    /**
     * @author Robert Simionescu
     * Instantiates a Street. Takes a street name, colour, and the initial cost.
     *
     * @param name The name of the street as a string.
     * @param colour The colour of the street as a string.
     * @param streetCost The cost of the street as an int.
     * @param houseCost The cost to build a house on the street
     * @param hotelCost The cost to build a hotel on the street (Must have all houses built first on each property)
     */
    public Street(String name, String colour, int streetCost, int houseCost, int hotelCost)
    {
        super(name, streetCost);
        this.colour = colour;

        this.houses = 0;
        this.hotel = 0;

        this.houseCost = houseCost;
        this.hotelCost = hotelCost;

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
     * Get the number of houses on this street
     *
     * @return      an int, the number of houses built by the player on this street
     */
    public int getHouses() {
        return this.houses;
    }

    /**
     * Get the number of hotels on this street
     *
     * @return      an int, the number of hotels built by the player on this street
     */
    public int getHotel() {
        return this.hotel;
    }

    /**
     * Get the amount it costs to build a house on this street
     *
     * @return      an int, the cost of the house
     */
    public int getHouseCost() {
        return this.houseCost;
    }

    /**
     * Get the amount it costs to build a hotel on this street
     * Precondition: The user must have 4 houses on their street before building a hotel.
     *
     * @return      an int, the cost of the hotel
     */
    public int getHotelCost() {
        return this.hotelCost;
    }

    /**
     * Set the number of houses there are on this street
     *
     * @param numHouses     an int, the total number of houses on this street
     */
    public void setHouses(int numHouses) {
        this.houses = numHouses;
    }

    /**
     * Set the number of hotels there are on this street
     *
     * @param numHotels     an int, the total number of hotels on this street
     */
    public void setHotels(int numHotels) {
        this.hotel = numHotels;
    }

    /**
     * Get the maximum capacity of the number of houses/hotels the player
     * is able to build on the street
     *
     * @return      a boolean, true if the maximum capacity is reached, false otherwise
     */
    public boolean getMaxCapacityReached() {
        return this.maxCapacityReached;
    }

    /**
     * Set the maximum capacity of the number of houses/hotels the player
     * is able to build on this street
     *
     * @param maxCapacityReached    a boolean, true if the maximum capacity is reached, false otherwise
     */
    public void setMaxCapacityReached(boolean maxCapacityReached) {
        this.maxCapacityReached = maxCapacityReached;
    }


    /**
     * @author Yash Kapoor
     *
     * Ensures the player owns this street and other streets of the same color as this street.
     *
     * @param player        a Player Object, the player that wants to build houses/hotels on this street.
     * @param gameboard     The gameboard on which the square is found. Rent in properties depends on which other properties
     *                      are owned by the same player, so this is necessary.
     *
     * @return              an ArrayList of all the streets of the same color if the owner owns
     *                      all the streets of the same color set.
     *
     *                      Null is returned if the owner does not own all the streets of the same color.
     */
    public ArrayList<Street> buildHouses(Player player, Gameboard gameboard)  {

        ArrayList<Street> ownedSquaresMatching = new ArrayList();

        // Iterates over all owned properties and counts how many are streets of the same colour as this one.
        int ownedSquaresMatchingColour = 0;

        for (Property property : player.getProperties()) {
            if (property instanceof Street) {
                if (((Street) property).getColour().equals(this.colour)) {
                    ownedSquaresMatching.add((Street) property);
                    ownedSquaresMatchingColour++;
                }
            }
        }

        // Iterates over all squares on the gameboard and counts how many are streets of the same colour as this one.
        ArrayList<Square> gameboardSquares = gameboard.getSquares();

        int totalSquaresMatchingColour = 0;

        for (Square square : gameboardSquares) {
            if (square instanceof Street) {
                if (((Street) square).getColour().equals(this.colour)) {
                    totalSquaresMatchingColour++;
                }
            }
        }

        // owner owns all the streets of the same color set, so houses can start getting built
        if (ownedSquaresMatchingColour == totalSquaresMatchingColour) {
            return ownedSquaresMatching;
        }

        // Otherwise, null is returned
        return null;
    }


    /**
     * Robert Simionescu and Yash Kapoor
     * Calculates and returns the rent for players landing on this street. Rent begins at 10% of the cost of the street
     * and goes up to 20% if the owner of the street owns all of the streets with this colour.
     * Houses and Hotels increase the rent of the property even further.
     *
     * Each house that is built on the street increases the rent by 10%. Hence, hotels increase the original rent
     * by 50%.
     *
     *
     * @param player        a Player object, the player who landed on the owner's property
     * @param gameboard     The gameboard on which the square is found. Rent in properties depends on which other properties
     *                      are owned by the same player, so this is necessary.
     * @return The rent of the street rounded down as an int.
    */
    @Override
    public int calculateRent(Player player, Gameboard gameboard) {

        double rent = this.getCost() * 0.1;

        Player ownerProperty = this.getOwner();

        // If the owner of this square does not own all of the streets of this colour, the rent is 10% of the cost.
        if (buildHouses(ownerProperty, gameboard) == null) {
            return (int) rent;
        }

        // If the owner of the square owns all streets of this colour, the rent is 20% of the cost.
        // Houses and hotels are accounted below
        if (this.houses == 0 && buildHouses(ownerProperty, gameboard) != null) {
            // all streets of the same color are owned
            return (int) (rent * 2);
        }
        else if (this.houses == 1) {
            return (int) (rent * 3);
        }
        else if (this.houses == 2) {
            return (int) (rent * 4);
        }
        else if (this.houses == 3) {
            return (int) (rent * 5);
        }
        else if (this.houses == 4) {
            return (int) (rent * 6);
        }
        else {
            // Otherwise, hotel is owned
            return (int) (rent * 7);
        }
    }

    /**
     * @author Yash Kapoor
     *
     * Shows the user a text representation of what street they are currently on.
     *
     * @return      a String, the name of the square
     */
    @Override
    public String toString() {
        return String.format("%s", getName());
    }
}
