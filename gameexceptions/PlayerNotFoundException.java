package gameexceptions;

/**
 * @author Robert Simionescu
 * Exception used to indicate that the user has tried to get a player that does not exist.
 */
public class PlayerNotFoundException extends RuntimeException
{
    public PlayerNotFoundException(String message)
    {
        super(message);
    }
}
