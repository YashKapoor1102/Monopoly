package gameexceptions;

/**
 * @author Robert Simionescu
 * Exception used to indicate that adding another player would exceed the maximum number of players.
 */
public class TooManyPlayersException extends RuntimeException
{
    public TooManyPlayersException(String message)
    {
        super(message);
    }
}
