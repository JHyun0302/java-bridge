package bridge.validation;

import static bridge.exception.ErrorMessage.IS_NOT_INTEGER;

import bridge.exception.CustomException;

public class BridgeValidator {
    public int isInteger(String input) {
        if (!isNumeric(input)) {
            throw CustomException.errorMessage(IS_NOT_INTEGER);
        }
        return Integer.parseInt(input);
    }

    private boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
