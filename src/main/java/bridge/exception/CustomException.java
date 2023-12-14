package bridge.exception;

public class CustomException extends IllegalArgumentException {

    private static final String PREFIX = "[ERROR] ";

    private CustomException(ErrorMessage errorMessage) {
        super(PREFIX + errorMessage.getMessage());
    }

    public static CustomException errorMessage(ErrorMessage errorMessage) {
        return new CustomException(errorMessage);
    }
}
