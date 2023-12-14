package bridge.config;

import bridge.validation.BridgeValidator;
import bridge.validation.CommonValidator;
import bridge.view.InputView;

public class Configuration {
    public InputView createInputView() {
        BridgeValidator bridgeValidator = new BridgeValidator();
        CommonValidator commonValidator = new CommonValidator();
        return new InputView(commonValidator, bridgeValidator);
    }
}
