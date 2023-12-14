package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeNumberGenerator;
import bridge.exception.CustomException;
import bridge.model.RetryCommand;
import bridge.service.BridgeGameService;
import bridge.view.InputView;
import bridge.view.OutputView;

public class BridgeController {
    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeMaker bridgeMaker;
    private BridgeGameService bridgeGame;

    public BridgeController(InputView inputView, OutputView outputView, BridgeNumberGenerator generator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeMaker = new BridgeMaker(generator);
    }

    public void run() {
        bridgeGame = initGame();
        playGame();
        showResult();
    }

    private BridgeGameService initGame() {
        try {
            return new BridgeGameService(bridgeMaker, inputView.readBridgeSize());
        } catch (CustomException e) {
            outputView.printError(e.getMessage());
            return initGame();
        }
    }

    private void playGame() {
        RetryCommand command = null;
        while (!bridgeGame.isComplete() && !RetryCommand.QUIT.equals(command)) {
            bridgeGame.move(inputView.readMoving());
            outputView.printMap(bridgeGame.makeBridge());
            if (bridgeGame.isEnd()) {
                command = inputView.readGameCommand();
                resetGame(command);
            }
        }
    }

    private void resetGame(RetryCommand command) {
        if (command.equals(RetryCommand.RETRY)) {
            bridgeGame.retry();
        }
    }

    private void showResult() {
        outputView.printResult(bridgeGame.makeBridge(), bridgeGame.getResult(),
                bridgeGame.getTotalAttempt());
    }
}
