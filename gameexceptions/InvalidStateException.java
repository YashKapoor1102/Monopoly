package gameexceptions;

/**
 * @author Robert Simionescu
 * This exception is used when a game method is called that cannot be done from the current game state.
 */
public class InvalidStateException extends RuntimeException
{
    public InvalidStateException(String message)
    {
        super(message);
    }
}
