package application.Exception;

public class ExistedWordException extends InvalidWordException {
    public ExistedWordException(String message) {
        super(message);
    }
}
