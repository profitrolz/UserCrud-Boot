package academy.kata.exception;


public class UserControllerException extends RuntimeException {
    public UserControllerException(String message) {
        super(message);
    }

    public UserControllerException(String message, Throwable cause) {
        super(message, cause);
    }
}
