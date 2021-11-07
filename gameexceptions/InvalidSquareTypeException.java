package gameexceptions;

/**
 * @author Robert Simionescu
 * This exception is thrown when a player attempts to purchase a square that is not a property.
 */
public class InvalidSquareTypeException extends RuntimeException
{
    public InvalidSquareTypeException(String message)
    {
        super(message);
    }
}
