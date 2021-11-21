package gameexceptions;

/**
 * @author Yash Kapoor
 * Exception used to indicate that the player must build houses evenly rather
 * than building all of them on one street.
 */
public class BuildHousesException extends RuntimeException {
    public BuildHousesException(String message) {
        super(message);
    }
}
