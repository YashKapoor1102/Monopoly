package gameexceptions;

public class NotEnoughHotelsException extends RuntimeException {
    public NotEnoughHotelsException(String message) {
        super(message);
    }
}
