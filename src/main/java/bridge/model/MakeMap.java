package bridge.model;

import java.util.ArrayList;
import java.util.List;

public class MakeMap {
    private static final String MAP_FORMAT = "[%s]\n";
    private static final String MAP_DELIMITER = "|";
    private static final String EMPTY_SPACE = "   ";
    private final List<String> upperMap = new ArrayList<>();
    private final List<String> lowerMap = new ArrayList<>();

    public void addMap(Move move, String sign) {
        if (move.equals(Move.UP)) {
            addUpperMap(sign);
            return;
        }
        addLowerMap(sign);
    }

    private void addUpperMap(String sign) {
        upperMap.add(sign);
        lowerMap.add(EMPTY_SPACE);
    }

    private void addLowerMap(String sign) {
        upperMap.add(EMPTY_SPACE);
        lowerMap.add(sign);
    }

    @Override
    public String toString() {
        return String.format(MAP_FORMAT, String.join(MAP_DELIMITER, upperMap)) +
                String.format(MAP_FORMAT, String.join(MAP_DELIMITER, lowerMap));
    }
}
