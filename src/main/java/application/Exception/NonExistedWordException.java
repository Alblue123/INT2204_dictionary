package application.Exception;

public class NonExistedWordException extends InvalidWordException {
    public NonExistedWordException(String message) {
        super(message);
    }
}
