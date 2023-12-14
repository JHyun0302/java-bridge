package bridge.validation;

import static bridge.exception.ErrorMessage.CANNOT_BE_NULL_OR_EMPTY;

import bridge.exception.CustomException;

public class CommonValidator {
    public String isBlank(String input) {
        if (input == null || input.isEmpty()) {
            throw CustomException.errorMessage(CANNOT_BE_NULL_OR_EMPTY);
        }
        return input;
    }
}
