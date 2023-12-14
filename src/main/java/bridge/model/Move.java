package bridge.model;

import static bridge.exception.ErrorMessage.INVALID_BRIDGE_NUMBER;
import static bridge.exception.ErrorMessage.INVALID_DIRECTION;

import bridge.exception.CustomException;
import java.util.Arrays;

public enum Move {
    DOWN("D", 0),
    UP("U", 1);


    private final String direction;
    private final int bridgeNumber;

    Move(String direction, int bridgeNumber) {
        this.direction = direction;
        this.bridgeNumber = bridgeNumber;
    }

    public static Move getValue(String direction) {
        return Arrays.stream(values())
                .filter(move -> move.direction.equals(direction))
                .findFirst()
                .orElseThrow(() -> CustomException.errorMessage(INVALID_DIRECTION));
    }

    public static Move getValue(int bridgeNumber) {
        return Arrays.stream(values())
                .filter(move -> move.bridgeNumber == bridgeNumber)
                .findFirst()
                .orElseThrow(() -> CustomException.errorMessage(INVALID_BRIDGE_NUMBER));
    }

    public String getDirection() {
        return direction;
    }
}
