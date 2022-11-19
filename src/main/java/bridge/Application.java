package bridge;

public class Application {
    private static final BridgeNumberGenerator bridgeNumberGenerator = null;

    public static void main(String[] args) {
        InputView input = new InputView();
        OutputView output = new OutputView();
        BridgeGame gamestart = new BridgeGame();
        BridgeMaker bridgeMake = new BridgeMaker(bridgeNumberGenerator);
        System.out.println("다리 건너기 게임을 시작합니다.\n");

        System.out.println("다리의 길이를 입력해주세요.");
        int bridgeLength = input.readBridgeSize();
        if (bridgeLength == 0) {
            return;
        } else if (bridgeLength < 3 || bridgeLength > 21) {
            System.out.println("[ERROR] 다리 길이는 3이상 20이하 숫자를 입력하세요.");
            return;
        }
        int tryCount = 1; // 총 시도 횟수
        int saveLength = bridgeLength;
        int savecnt = 0;
        while (bridgeLength > 0) {
            bridgeMake.makeBridge(bridgeLength); // 맵 생성
            if (bridgeMake.firstORretry == false)
                bridgeMake.saveIdx();
            if (bridgeMake.error.contains("ERROR")) {
                System.out.println("[ERROR] U 또는 D를 입력하세요.");
                return;
            }

            output.printMap(bridgeMake); // Map 출력
            savecnt = bridgeMake.saveIDX;
            if (bridgeMake.upperMap.contains("X") || bridgeMake.lowerMap.contains("X")) {
                System.out.println("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
                String retry = gamestart.retry(input.readGameCommand()); // Q or R
                if (retry.equals("R")) {
                    bridgeMake.firstORretry = true;// 재시도
                    bridgeMake.saveIDX = savecnt;
                    bridgeMake.i = 0;
                    tryCount += 1;
                    bridgeLength = saveLength + 1;
                    bridgeMake.upperMap = "[ ";
                    bridgeMake.lowerMap = "[ ";

                } else if (retry.equals("Q")) {
                    output.printResult(bridgeMake, tryCount); /** 최종결과 출력**/

                    break;
                } else if (retry.equals("ERROR")) {
                    System.out.println("[ERROR] R 또는 Q를 입력하세요.");
                    return;
                }
            }
            bridgeLength--;
        }
        output.printResult(bridgeMake, tryCount);

        /**
         * 이동할 칸 입력받기(U OR D)
         * move() 메소드 이용해서 U OR D 받기
         *
         * 입력에 따른 MAP 출력
         * outputview.printmap 메소드 이용해서 map 출력
         *
         * if) map == O
         *     bridgemaker의 upper, lowermap 리스트 안의 값 확인하기
         *     if(uppermap.get() == "o" || lowermap.get() == "o")
         *            계속 진행
         *     계속 진행
         *     map == X
         *     bridgemaker의 upper, lowermap 리스트 안의 값 확인하기
         *     if(uppermap.get() == "x" || lowermap.get() == "x")
         *           inputview.readGameCommand() 이용해서 Q or R 받기
         *
         *     bridgegame.retry() 메소드 이용
         *
         * 재시작 = R
         *  다시 진행
         *  재시작 = Q
         *  최종 결과 출력
         */


    }
}

