package bridge.model;

public class Player {
    private final String moveCommand;
    private final Boolean isSuccess;
    private final int retryCount;

    private Player(String moveCommand) {
        this.moveCommand = moveCommand;
        isSuccess = false;
        retryCount = 0;
    }

    public static Player createPlayer(String moveCommand) {
        return new Player(moveCommand);
    }


}
