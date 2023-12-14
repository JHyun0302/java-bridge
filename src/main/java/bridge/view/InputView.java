package bridge.view;

import bridge.exception.CustomException;
import bridge.model.Move;
import bridge.model.RetryCommand;
import bridge.validation.BridgeValidator;
import bridge.validation.CommonValidator;
import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {
    private static final String GAME_START = "다리 건너기 게임을 시작합니다.";

    private static final String BRIDGE_LENGTH = "다리의 길이를 입력해주세요.";
    private static final String SELECT_MOVE_COMMAND = "이동할 칸을 선택해주세요. (위: U, 아래: D)";
    private static final String NOTICE = "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)";

    private final CommonValidator commonValidator;
    private final BridgeValidator bridgeValidator;

    public InputView(CommonValidator commonValidator, BridgeValidator bridgeValidator) {
        this.commonValidator = commonValidator;
        this.bridgeValidator = bridgeValidator;
    }

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        System.out.println(GAME_START);
        System.out.println(BRIDGE_LENGTH);
        try {
            String input = commonValidator.isBlank(Console.readLine());
            return bridgeValidator.isInteger(input);
        } catch (CustomException e) {
            System.out.println(e.getMessage());
            return readBridgeSize();
        }
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public Move readMoving() {
        System.out.println(SELECT_MOVE_COMMAND);
        try {
            return Move.getValue(commonValidator.isBlank(Console.readLine()));
        } catch (CustomException e) {
            System.out.println(e.getMessage());
            return readMoving();
        }
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public RetryCommand readGameCommand() {
        System.out.println(NOTICE);
        try {
            return RetryCommand.getValue(commonValidator.isBlank(Console.readLine()));
        } catch (CustomException e) {
            System.out.println(e.getMessage());
            return readGameCommand();
        }
    }
}