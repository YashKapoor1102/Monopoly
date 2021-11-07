package gameexceptions;

/**
 * @author Robert Simionescu
 * Exception used to indicate that the user has tried to enter another user with the same name.
 */
public class DuplicateNameException extends RuntimeException
{
    public DuplicateNameException(String message)
    {
        super(message);
    }
}
