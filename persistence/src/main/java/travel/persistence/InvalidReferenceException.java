package travel.persistence;

public class InvalidReferenceException extends RuntimeException{
    public InvalidReferenceException(String message) {
        super(message);
    }
}
