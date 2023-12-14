package bridge.model;

import static bridge.exception.ErrorMessage.INVALID_RETRY_COMMAND;

import bridge.exception.CustomException;
import java.util.Arrays;

public enum RetryCommand {
    RETRY("R"),
    QUIT("Q");

    private final String command;

    RetryCommand(String command) {
        this.command = command;
    }

    public static RetryCommand getValue(String command) {
        return Arrays.stream(values())
                .filter(retryCommand -> retryCommand.command.equals(command))
                .findFirst()
                .orElseThrow(() -> CustomException.errorMessage(INVALID_RETRY_COMMAND));
    }
}
