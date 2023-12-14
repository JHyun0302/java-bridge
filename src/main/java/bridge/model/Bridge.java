package bridge.model;

import static bridge.exception.ErrorMessage.INVALID_BRIDGE_SIZE;
import static bridge.model.constant.BridgeGameConstant.FAIL_SIGN;
import static bridge.model.constant.BridgeGameConstant.PASS_SIGN;

import bridge.exception.CustomException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Bridge {
    private static final int INIT_INDEX = 0;
    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 20;
    private final List<Move> bridge;

    private Bridge(List<String> bridge) {
        validateBridgeSize(bridge);
        this.bridge = bridge.stream().map(Move::getValue).collect(Collectors.toList());
    }

    public static Bridge createSystemBridge(List<String> bridge) {
        return new Bridge(bridge);
    }

    private Bridge() {
        this.bridge = new ArrayList<>();
    }

    public static Bridge createUserBridge() {
        return new Bridge();
    }

    public void addMove(Move move) {
        bridge.add(move);
    }

    public Move getMove(int index) {
        return bridge.get(index);
    }


    public int getBridgeSize() {
        return bridge.size();
    }


    public List<String> makeCompareResult(Bridge other) {
        int shortSize = Math.min(this.bridge.size(), other.getBridgeSize());
        List<String> list = new ArrayList<>();
        return IntStream.range(INIT_INDEX, shortSize)
                .mapToObj(index -> isEqualToIndex(index, other.bridge.get(index)))
                .map(this::successOrFail)
                .collect(Collectors.toList());
    }

    private String successOrFail(boolean equalToIndex) {
        if (equalToIndex) {
            return PASS_SIGN;
        }
        return FAIL_SIGN;
    }

    private boolean isEqualToIndex(int index, Move value) {
        return bridge.get(index).equals(value);
    }


    private void validateBridgeSize(List<String> bridge) {
        if (bridge.size() < MIN_SIZE || bridge.size() > MAX_SIZE) {
            throw CustomException.errorMessage(INVALID_BRIDGE_SIZE);
        }
    }
}
