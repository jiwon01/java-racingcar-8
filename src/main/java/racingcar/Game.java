package racingcar;

import camp.nextstep.edu.missionutils.*;

public class Game {
    public void StartGame() {
        // 자동차 이름 목록 입력
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String userInput = Console.readLine();

        // 유효성 검증
        validateCarNames(userInput);

        // 자동차 목록 저장
        String[] carNames = userInput.split(",");

        System.out.println("시도할 횟수는 몇 회인가요?");
        Integer userCountInput = Integer.valueOf(Console.readLine());
    }

    /**
     * 자동차 이름 입력 유효성 검증
     */
    private void validateCarNames(String input) {
        // 입력값이 비거나 null인 경우
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("입력값이 비었습니다");
        }

        String[] carNames = input.split("\\s*,\\s*");

        for (String name : carNames) {
            if (name.isEmpty()) {
                throw new IllegalArgumentException("자동차 이름이 비었습니다");
            }
            if (name.length() > 5) {
                throw new IllegalArgumentException("자동차의 이름이 5자를 초과했습니다");
            }
        }
    }

    /**
     * 랜덤 수 뽑기
     */
    private Integer getRandomNumber() {
        return Randoms.pickNumberInRange(0, 9);
    }
}
