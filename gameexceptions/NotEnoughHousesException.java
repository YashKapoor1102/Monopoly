package gameexceptions;

/**
 * Exception used to indicate that there are not enough houses available
 * in the bank for the player to build one on their street.
 *
 * There are a total of 32 houses in the bank in Monopoly.
 */
public class NotEnoughHousesException extends RuntimeException {
    public NotEnoughHousesException(String message) {
        super(message);
    }
}
