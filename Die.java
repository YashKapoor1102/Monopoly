/**
 * @author Yash Kapoor
 * @version Milestone 4
 */

import java.util.Random;

/**
 * @author Yash Kapoor
 * Die Class that creates a die with
 * a specific number of sides.
 *
 * Default is the die has 6 sides, meaning the highest number
 * the die can roll is 6 and the lowest it can roll is 1.
 */
public class Die {

    private final int NUM_SIDES;

    /**
     * @author Yash Kapoor
     * Default constructor for Die
     */
    public Die() {
        this.NUM_SIDES = 6;
    }

    /**
     * @author Yash Kapoor
     * Constructor for Die
     *
     * @param numberOfSides     an int, NUM_SIDES the die has
     */
    public Die(int numberOfSides) {
        this.NUM_SIDES = numberOfSides;
    }

    /**
     * @author Yash Kapoor
     * Returns a random integer value in the range [1, NUM_SIDES]
     *
     * @return      an int, randomly generated value between 1 and the number of sides the die has inclusive
     */
    public int roll() {
        Random rnd = new Random();

        return rnd.nextInt(NUM_SIDES) + 1;
    }


}
