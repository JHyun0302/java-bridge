package bridge.exception;

public enum ErrorMessage {
    CANNOT_BE_NULL_OR_EMPTY("입력값이 빈 값이거나 null 값 일 수 없습니다."),
    IS_NOT_INTEGER("입력값은 정수 값이여야 합니다."),
    INVALID_DIRECTION("이동 방향은 U / D 값으로만 입력 가능합니다."),
    INVALID_BRIDGE_NUMBER("이동 방향은 0 / 1 값으로만 입력 가능합니다."),
    INVALID_RETRY_COMMAND("입력값은 R / Q 중 하나만 입력이 가능합니다."),
    INVALID_BRIDGE_SIZE("다리의 길이는 3이상 20이하로 입력해야 합니다.");

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}