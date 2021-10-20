import java.util.Random;

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

    /**
     * Get the number of sides the die has
     *
     * @return      an int, number of sides the die has
     */
    public int getNumberOfSides() {
        return this.numberOfSides;
    }



}
