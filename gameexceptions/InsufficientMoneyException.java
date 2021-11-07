package gameexceptions;

/**
 * @author Robert Simionescu
 * This exception is thrown when a player attempts to make a purchase they cannot afford.
 */
public class InsufficientMoneyException extends RuntimeException
{
    public InsufficientMoneyException(String message)
    {
        super(message);
    }
}
