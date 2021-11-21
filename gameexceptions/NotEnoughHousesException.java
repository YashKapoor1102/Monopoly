package gameexceptions;

public class NotEnoughHousesException extends RuntimeException {
    public NotEnoughHousesException(String message) {
        super(message);
    }
}
