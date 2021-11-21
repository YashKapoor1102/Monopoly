package gameexceptions;

/**
 * @author Yash Kapoor
 * Exception used to indicate that there are not enough hotels available in the bank
 * for the player to build one on their street.
 *
 * There are a total of 12 hotels in the bank in Monopoly.
 */
public class NotEnoughHotelsException extends RuntimeException {
    public NotEnoughHotelsException(String message) {
        super(message);
    }
}
