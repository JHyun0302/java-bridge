package bridge.service;

import static bridge.model.constant.BridgeGameConstant.FAIL_SIGN;
import static bridge.model.constant.BridgeGameConstant.GAME_RESULT_FAIL;
import static bridge.model.constant.BridgeGameConstant.GAME_RESULT_SUCCESS;

import bridge.BridgeMaker;
import bridge.model.Bridge;
import bridge.model.MakeMap;
import bridge.model.Move;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGameService {
    private static final int INITIAL_ATTEMPT = 0;
    private static final int INITIAL_INDEX = 0;
    private int totalAttempt = INITIAL_ATTEMPT;
    private final Bridge systempBridge;
    private Bridge userBridge = Bridge.createUserBridge();

    public BridgeGameService(BridgeMaker bridgeMaker, int size) {
        systempBridge = Bridge.createSystemBridge(bridgeMaker.makeBridge(size));
        totalAttempt++;
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(Move move) {
        userBridge.addMove(move);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        userBridge = Bridge.createUserBridge();
        totalAttempt++;
    }

    public MakeMap makeBridge() {
        MakeMap makeMap = new MakeMap();
        List<String> compareResult = systempBridge.makeCompareResult(userBridge);
        int index = INITIAL_INDEX;
        while (index < compareResult.size()) {
            makeMap.addMap(userBridge.getMove(index), compareResult.get(index));
            index++;
        }
        return makeMap;
    }

    public boolean isComplete() {
        List<String> compareResult = systempBridge.makeCompareResult(userBridge);
        return compareResult.size() == systempBridge.getBridgeSize()
                && !compareResult.contains(FAIL_SIGN);
    }

    public boolean isEnd() {
        return systempBridge.makeCompareResult(userBridge).contains(FAIL_SIGN);
    }

    public String getResult() {
        if (isComplete()) {
            return GAME_RESULT_SUCCESS;
        }
        return GAME_RESULT_FAIL;
    }

    public int getTotalAttempt() {
        return totalAttempt;
    }

}
