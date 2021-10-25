/**
 * @author Yash Kapoor (Student ID: 101163338)
 * @version Milestone 1
 */

import java.util.Random;

/**
 * Die Class that creates a die with
 * a specific number of sides.
 *
 * Default is the die has 6 sides, meaning the highest number
 * the die can roll is 6 and the lowest it can roll is 1.
 */
public class Die {

    private final int numberOfSides;

    /**
     * Default constructor for Die
     */
    public Die() {
        this.numberOfSides = 6;
    }

    /**
     * Constructor for Die
     *
     * @param numberOfSides     an int, numberOfSides the die has
     */
    public Die(int numberOfSides) {
        this.numberOfSides = numberOfSides;
    }

    /**
     * Returns a random integer value in the range [1, numberOfSides]
     *
     * @return      an int, randomly generated value between 1 and the number of sides the die has inclusive
     */
    public int roll() {
        Random rnd = new Random();

        return rnd.nextInt(numberOfSides) + 1;
    }


}
