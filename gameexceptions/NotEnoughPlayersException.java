package gameexceptions;

/**
 * @author Robert Simionescu
 * Exception used to indicate that not enough players have been added when attempting to start the game with less than
 * the minimum number of players.
 */
public class NotEnoughPlayersException extends RuntimeException
{
    public NotEnoughPlayersException(String message)
    {
        super(message);
    }
}
