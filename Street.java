/**
 * @author Robert Simionescu and Yash Kapoor
 * @version Milestone 4
 */

import java.io.Serializable;
import java.util.*;

/**
 * @author Robert Simionescu and Yash Kapoor
 * Class representing the different streets available in Monopoly. In addition to the name and cost of all properties,
 * Streets have a COLOUR that is used to determine rent costs and ability to build houses. If a player owns all streets
 * of the same COLOUR, the rent of all of those streets doubles from 10% of the cost to 20%.
 *
 * Building houses/hotels on the street increases the rent of the street even more, all the way up to 70% of the cost.
 */
public class Street extends Property implements Serializable
{
    private final String COLOUR;

    private static final double STARTING_RENT_PERCENT = 0.1;
    private static final double SAME_COLOUR_RENT_PERCENT = 0.2;
    private static final double ONE_HOUSE_RENT_PERCENT = 0.3;
    private static final double TWO_HOUSES_RENT_PERCENT = 0.4;
    private static final double THREE_HOUSES_RENT_PERCENT = 0.5;
    private static final double FOUR_HOUSES_RENT_PERCENT = 0.6;
    private static final double HOTEL_RENT_PERCENT = 0.7;

    private final int HOUSE_COST;
    private final int HOTEL_COST;

    private int houses;
    private int hotel;

    private boolean maxCapacityReached;

    /**
     * @author Robert Simionescu
     * Instantiates a Street. Takes a street name, COLOUR, and the initial cost.
     *
     * @param name The name of the street as a string.
     * @param colour The COLOUR of the street as a string.
     * @param streetCost The cost of the street as an int.
     * @param houseCost The cost to build a house on the street
     * @param hotelCost The cost to build a hotel on the street (Must have all houses built first on each property)
     */
    public Street(String name, String colour, int streetCost, int houseCost, int hotelCost)
    {
        super(name, streetCost);
        this.COLOUR = colour;

        this.houses = 0;
        this.hotel = 0;

        this.HOUSE_COST = houseCost;
        this.HOTEL_COST = hotelCost;

        this.maxCapacityReached = false;

    }

    /**
     * @author Robert Simionescu
     * Returns the COLOUR of the street.
     * @return The COLOUR of the street as a String.
     */
    public String getColour()
    {
        return this.COLOUR;
    }

    /**
     * @author Yash Kapoor
     * Get the number of houses on this street
     *
     * @return      an int, the number of houses built by the player on this street
     */
    public int getHouses() {
        return this.houses;
    }

    /**
     * @author Yash Kapoor
     * Get the number of hotels on this street
     *
     * @return      an int, the number of hotels built by the player on this street
     */
    public int getHotel() {
        return this.hotel;
    }

    /**
     * @author Yash Kapoor
     * Get the amount it costs to build a house on this street
     *
     * @return      an int, the cost of the house
     */
    public int getHouseCost() {
        return this.HOUSE_COST;
    }

    /**
     * @author Yash Kapoor
     * Get the amount it costs to build a hotel on this street
     * Precondition: The user must have 4 houses on their street before building a hotel.
     *
     * @return      an int, the cost of the hotel
     */
    public int getHotelCost() {
        return this.HOTEL_COST;
    }

    /**
     * @author Yash Kapoor
     * Set the number of houses there are on this street
     *
     * @param numHouses     an int, the total number of houses on this street
     */
    public void setHouses(int numHouses) {
        this.houses = numHouses;
    }

    /**
     * @author Yash Kapoor
     * Set the number of hotels there are on this street
     *
     * @param numHotels     an int, the total number of hotels on this street
     */
    public void setHotels(int numHotels) {
        this.hotel = numHotels;
    }

    /**
     * @author Yash Kapoor
     * Set the maximum capacity of the number of houses/hotels the player
     * is able to build on the street
     *
     * @param maxCapacityReached    a boolean, true if the maximum capacity is reached, false otherwise
     */
    public void setMaxCapacityReached(boolean maxCapacityReached) {
        this.maxCapacityReached = maxCapacityReached;
    }

    /**
     * @author Yash Kapoor
     * Get the maximum capacity of the number of houses/hotels the player
     * is able to build on this street
     *
     * @return      a boolean, true if the maximum capacity is reached, false otherwise
     */
    public boolean getMaxCapacityReached() {
        return this.maxCapacityReached;
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
    public ArrayList<Street> getBuildableColour(Player player, Gameboard gameboard)  {

        ArrayList<Street> ownedSquaresMatching = new ArrayList<Street>();

        // Iterates over all owned properties and counts how many are streets of the same COLOUR as this one.
        int ownedSquaresMatchingColour = 0;

        for (Property property : player.getProperties()) {
            if (property instanceof Street) {
                if (((Street) property).getColour().equals(this.COLOUR)) {
                    ownedSquaresMatching.add((Street) property);
                    ownedSquaresMatchingColour++;
                }
            }
        }

        // Iterates over all squares on the gameboard and counts how many are streets of the same COLOUR as this one.
        ArrayList<Square> gameboardSquares = gameboard.getSquares();

        int totalSquaresMatchingColour = 0;

        for (Square square : gameboardSquares) {
            if (square instanceof Street) {
                if (((Street) square).getColour().equals(this.COLOUR)) {
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
     * and goes up to 20% if the owner of the street owns all of the streets with this COLOUR.
     * Houses and Hotels increase the rent of the property even further.
     *
     * Each house that is built on the street increases the rent by 10%. Hence, hotels increase the original rent
     * by 60%.
     *
     * @param player        a Player object, the player who landed on the owner's property
     * @param gameboard     The gameboard on which the square is found. Rent in properties depends on which other properties
     *                      are owned by the same player, so this is necessary.
     * @return The rent of the street rounded down as an int.
    */
    @Override
    public int calculateRent(Player player, Gameboard gameboard) {

        double startingRent, sameColourRent, oneHouseRent, twoHousesRent, threeHousesRent, fourHousesRent, hotelRent;

        startingRent = this.getCost() * STARTING_RENT_PERCENT;
        sameColourRent = this.getCost() * SAME_COLOUR_RENT_PERCENT;
        oneHouseRent = this.getCost() * ONE_HOUSE_RENT_PERCENT;
        twoHousesRent = this.getCost() * TWO_HOUSES_RENT_PERCENT;
        threeHousesRent = this.getCost() * THREE_HOUSES_RENT_PERCENT;
        fourHousesRent = this.getCost() * FOUR_HOUSES_RENT_PERCENT;
        hotelRent = this.getCost() * HOTEL_RENT_PERCENT;

        Player ownerProperty = this.getOwner();

        // If the owner of this square does not own all of the streets of this COLOUR, the rent is 10% of the cost.
        if (getBuildableColour(ownerProperty, gameboard) == null) {
            return (int) startingRent;
        }

        // If the owner of the square owns all streets of this COLOUR, the rent is 20% of the cost.
        // Houses and hotels are accounted below
        if (this.houses == 0 && getBuildableColour(ownerProperty, gameboard) != null) {
            // all streets of the same color are owned
            return (int) (sameColourRent);
        }
        else if (this.houses == 1) {
            return (int) (oneHouseRent);
        }
        else if (this.houses == 2) {
            return (int) (twoHousesRent);
        }
        else if (this.houses == 3) {
            return (int) (threeHousesRent);
        }
        else if (this.houses == 4) {
            return (int) (fourHousesRent);
        }
        else {
            // Otherwise, hotel is owned
            return (int) (hotelRent);
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
