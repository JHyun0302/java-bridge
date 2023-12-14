package bridge;

import bridge.config.Configuration;
import bridge.controller.BridgeController;
import bridge.view.InputView;
import bridge.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        InputView inputView = configuration.createInputView();
        OutputView outputView = new OutputView();
        BridgeNumberGenerator generator = new BridgeRandomNumberGenerator();
        BridgeController bridgeController = new BridgeController(inputView, outputView, generator);

        bridgeController.run();
    }
}
