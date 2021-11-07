package gameexceptions;

/**
 * @author Robert Simionescu
 * This exception is thrown when a player attempts to purchase a property owned by another player.
 */
public class PropertyOwnedException extends RuntimeException
{
    public PropertyOwnedException(String message)
    {
        super(message);
    }
}
